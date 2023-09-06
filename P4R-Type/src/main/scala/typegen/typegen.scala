import Console.print
import scala.io.Source._
//import zio._
import zio.{RIO,ZIO,ZIOAppDefault,ZLayer}
import zio.Console.printLine
import scalapb.zio_grpc.{ServerMain, ServiceList}
import p4.v1.p4runtime.*
import p4.v1.p4runtime.ZioP4Runtime.*
import zio.stream.ZStream
import p4.v1.p4runtime.GetForwardingPipelineConfigRequest.ResponseType.ALL
import p4.v1.p4runtime.FieldMatch.FieldMatchType
import com.google.protobuf.ByteString
import org.checkerframework.checker.guieffect.qual.UI
import p4.v1.p4runtime.ZioP4Runtime.P4RuntimeClient.ZService
import p4.v1.p4runtime.DigestEntry.Config
import p4.config.v1.p4types.P4DataTypeSpec.TypeSpec.Bool
import p4.v1.p4runtime.P4RuntimeGrpc.P4RuntimeStub
import io.grpc.stub.StreamObserver
import io.grpc.CallOptions
import concurrent.ExecutionContext.Implicits.global
import p4.config.v1.p4info.P4Info
import scala.util.Success
import scala.util.Failure
import java.io.FileInputStream
import java.io.InputStreamReader
import com.google.protobuf.TextFormat
import p4.config.v1.p4info.P4InfoProto
import com.google.protobuf.CodedInputStream
import com.google.protobuf.CodedOutputStream
import java.io.OutputStream
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import scalapb.json4s.{JsonFormat, Parser}
import org.json4s.Reader
import p4.config.v1.p4info.{Action => P4InfoAction, _}
import p4.config.v1.p4info.MatchField.MatchType.EXACT
import p4.config.v1.p4info.MatchField.MatchType.LPM
import p4.config.v1.p4info.MatchField.MatchType.OPTIONAL
import p4.config.v1.p4info.MatchField.MatchType.RANGE
import p4.config.v1.p4info.MatchField.MatchType.TERNARY
import p4.config.v1.p4info.Action.Param
import java.nio.file.Files
import java.nio.charset.StandardCharsets
import java.nio.file.Paths
import zio.stream.ZSink


// === Helper functions ===
def mapM[A,B](as : Seq[A], op : A => RIO[Unit, B]) : RIO[Unit, Seq[B]] =
  val m_as = as.map(op)
  m_as.foldLeft[RIO[Unit, Seq[B]]](ZIO.succeed(Seq[B]()))((m_res, m_e) => for {
    res <- m_res
    e <- m_e
  } yield (res.:+(e)))

def genImports() : RIO[Unit, String] = ZIO.succeed(
  "import p4rtype.{Exact, LPM, Optional, Range, Ternary, P4RTypeRuntimeObserver}\n" +
  "import com.google.protobuf.ByteString\n" +
  "import p4.v1.p4runtime.FieldMatch\n" +
  "import p4.v1.p4runtime.FieldMatch.FieldMatchType\n" +
  "import p4.v1.p4runtime.TableAction\n" +
  "import p4.v1.p4runtime.Action\n" +
  "import p4.v1.p4runtime.P4RuntimeGrpc.P4RuntimeStub\n" +
  "import p4.v1.p4runtime.TableEntry\n" +
  "import io.grpc.ManagedChannelBuilder\n" +
  "import p4.v1.p4runtime.StreamMessageRequest\n" +
  "import p4.v1.p4runtime.MasterArbitrationUpdate\n" +
  "import p4.v1.p4runtime.Uint128\n" +
  "import io.grpc.CallOptions\n" +
  "import p4.v1.p4runtime.StreamMessageResponse\n" +
  "import p4.v1.p4runtime.Action.Param"
)

// === Match types ===
def genMatchFieldArg(mf : MatchField) : RIO[Unit, String] =
  mf.`match`.matchType match
    case None => ZIO.fail(new Exception("Failure: Match field has no type."))
    case Some(tp) =>
      tp match
        case EXACT    => ZIO.succeed("\"" + mf.name + "\", Exact")
        case LPM      => ZIO.succeed("Option[(\"" + mf.name + "\", LPM)]")
        case RANGE    => ZIO.succeed("Option[(\"" + mf.name + "\", Range)]")
        case TERNARY  => ZIO.succeed("Option[(\"" + mf.name + "\", Ternary)]")
        case OPTIONAL => ZIO.succeed("Option[(\"" + mf.name + "\", Optional)]")
        case _ => ZIO.fail(new Exception("Failure: Invalid match field type"))

def genMatchFieldArgs(mfs : Seq[MatchField]) : RIO[Unit, String] = for {
  matchFieldArgs <- mapM(mfs, genMatchFieldArg)
} yield {
  if mfs.size > 0 then
    "(" + matchFieldArgs.reduce((a1, a2) => "(" + a1 + "), (" + a2 + ")") + ")"
  else
    "Unit"
}

def genTableMatchFields(tables : Seq[Table]) : RIO[Unit, String] = for {
  tableMatchFields <- mapM[Table,(String, Seq[MatchField])](tables, t => {
    t.preamble.fold
      (ifEmpty = ZIO.fail(new Exception("Failure: Table has empty preamble.")))
      (preamble => ZIO.succeed((preamble.name, t.matchFields)))
  })
  cases <- mapM(tableMatchFields, (tn, mfs) => for {
    args <- genMatchFieldArgs(mfs)
  } yield {
    "    case \"" + tn + "\" => " + args + " | \"*\""
  })
} yield {
  "type TableMatchFields[TN] =\n  TN match\n"
  + {
    if cases.size > 0 then
      cases.reduce((c1, c2) => c1 + "\n" + c2) + "\n"
    else
      ""
  }
  + "    case \"*\" => \"*\""
}

def genActions(actions : Seq[P4InfoAction]) : RIO[Unit, String] = for {
  actionNames <- mapM[P4InfoAction,String](actions, action => {
    action.preamble.fold
      (ifEmpty = ZIO.fail(new Exception("Failure: Action has empty preamble.")))
      (preamble => ZIO.succeed("\"" + preamble.name + "\""))
  })
} yield {
  if actionNames.size > 0 then
    actionNames.reduce((s1, s2) => s1 + " | " + s2)
  else
    ""
}

def genActionName(actions : Seq[P4InfoAction]) : RIO[Unit, String] = for {
  actions <- genActions(actions)
} yield {
  "type ActionName = " + actions + " | \"*\"\n"
}

def genTableAction(tables : Seq[Table], actions : Seq[P4InfoAction]) : RIO[Unit, String] = for {
  tableActions <- mapM[Table,(String, Seq[ActionRef])](tables, t => {
    t.preamble.fold
      (ifEmpty = ZIO.fail(new Exception("Failure: Table has empty preamble.")))
      (preamble => ZIO.succeed(preamble.name, t.actionRefs))
  })
  actionIdNames <- mapM[P4InfoAction, (Int, String)](actions, a => {
    a.preamble.fold
      (ifEmpty = ZIO.fail(new Exception("Failure: Action has empty preamble.")))
      (preamble => ZIO.succeed(preamble.id, preamble.name))
  })
  matchActionCases <- mapM(tableActions, (tn, ars) => {
    for {
      actionNames <- mapM(ars, ar => {
        actionIdNames.find((aid, an) => aid == ar.id) match
          case None => ZIO.fail(new Exception("Failure: Table has invaild action reference."))
          case Some((_, an)) => ZIO.succeed("\"" + an + "\"")
      })
    } yield {
      if actionNames.size > 0 then
        "    case \"" + tn + "\" => " + actionNames.reduce((an1, an2) => an1 + " | " + an2) + " | \"*\""
      else
        "    case \"" + tn + "\" => \"*\""
    }
  })
} yield {
  "type TableAction[TN] <: ActionName =\n  TN match\n"
  + matchActionCases.reduce((c1, c2) => c1 + "\n" + c2) + "\n"
  + "    case \"*\" => \"*\"\n"
}

def genActionParams(actions : Seq[P4InfoAction]) : RIO[Unit, String] = for {
  actionParams <- mapM[P4InfoAction, String](actions, a => {
    a.preamble.fold
      (ifEmpty = ZIO.fail(new Exception("Failure: Action has empty preamble.")))
      (preamble => ZIO.succeed({
        val paramTypes = a.params.map(p => "(\"" + p.name + "\", ByteString)")
        if paramTypes.size > 0 then
          "    case \"" + preamble.name + "\" => (" + paramTypes.reduce((p1, p2) => p1 + ", " + p2) + ")"
        else
          "    case \"" + preamble.name + "\" => Unit"
      }))
  })
} yield {
  "type ActionParams[AN] =\n  AN match\n"
  + {
    if actionParams.size > 0 then
      actionParams.reduce((p1, p2) => p1 + "\n" + p2) + "\n"
    else
      "\n"
  }
  + "    case \"*\" => \"*\"\n"
}

def genMatchTypes(p4info : P4Info) : RIO[Unit, String] = for {
  tableMatchFields <- genTableMatchFields(p4info.tables)
  actionName <- genActionName(p4info.actions)
  tableAction <- genTableAction(p4info.tables, p4info.actions)
  actionParams <- genActionParams(p4info.actions)
} yield {
  tableMatchFields + "\n" + actionName + "\n" + tableAction + "\n" + actionParams
}

// === Channel ===
def genTableToProto(tables : Seq[Table]) : RIO[Unit, String] = for {
  tableCases <- mapM[Table, String](tables, t => {
    t.preamble.fold
      (ifEmpty = ZIO.fail(new Exception("Failure: Table has empty preamble.")))
      (preamble => ZIO.succeed("        case \"" + preamble.name + "\" => " + preamble.id))
  })
} yield {
  "    val tableId =\n" +
  "      te.table match\n" +
  "        case \"*\" => 0\n" +
  {
    if tableCases.size > 0 then
      tableCases.reduce((c1, c2) => c1 + "\n" + c2) + "\n"
    else
      "\n"
  }
}

def genMatchFieldToProtoCase(table : String, mfs : Seq[MatchField]) : RIO[Unit, String] = for {
  fieldsIndexed <- ZIO.succeed(mfs.zipWithIndex)
  caseVars <- mapM(fieldsIndexed, (mf,idx) => {
    mf.`match`.matchType match
      case None => ZIO.fail(new Exception("Failure: MatchField has no match type."))
      case Some(value) =>
        value match
          case EXACT => ZIO.succeed("(_, t" + idx + ")")
          case _ => ZIO.succeed("t" + idx)
  })
  caseResult <- mapM(fieldsIndexed, (mf,idx) => {
    mf.`match`.matchType match
      case None => ZIO.fail(new Exception("Failure: MatchField has no match type."))
      case Some(value) =>
        value match
          case EXACT => ZIO.succeed("Seq(p4rtype.matchFieldToProto(" + mf.id + ", t" + idx + ".asInstanceOf[Exact]))")
          case _ => for {
              caseVarType <- genMatchFieldArg(mf)
            } yield {
              "t" + idx + ".asInstanceOf[" + caseVarType + "].map((_, t) => p4rtype.matchFieldToProto(" + mf.id + ", t)).toSeq"
            }
  })
} yield {
  if caseVars.size > 0 && caseResult.size > 0 then
    "        case (\"" + table + "\", (" + caseVars.reduce((v1, v2) => v1 + ", " + v2) + ")) => " + caseResult.reduce((v1, v2) => v1 + " ++ " + v2)
  else
    "        case (\"" + table + "\", _) => Seq.empty"
}

def genMatchFieldsToProto(tables : Seq[Table]) : RIO[Unit, String] = for {
  tableMatches <- mapM[Table, String](tables, t => {
    t.preamble.fold
      (ifEmpty = ZIO.fail(new Exception("Failure: Table has empty preamble.")))
      (preamble => genMatchFieldToProtoCase(preamble.name, t.matchFields))
  })
} yield {
  "    val matchFields =\n" +
  "      (te.table, te.matches) match\n" +
  "        case (\"*\", _) => Seq.empty\n" +
  "        case (_, _ : \"*\") => Seq.empty\n" +
  {
    if tableMatches.size > 0 then
      tableMatches.reduce((s1, s2) => s1 + "\n" + s2) + "\n"
    else
      "\n"
  }
}

def genActionToProto(actions : Seq[P4InfoAction]) : RIO[Unit, String] = for {
  actionCases <- mapM(actions, a => {
    a.preamble.fold
      (ifEmpty = ZIO.fail(new Exception("Failure: Action has empty preamble.")))
      (preamble => ZIO.succeed("        case \"" + preamble.name + "\" => " + preamble.id))
  })
} yield {
  "    val actionId =\n" +
  "      te.action match\n" +
  "        case \"*\" => 0\n" +
  {
    if actionCases.size > 0 then
      actionCases.reduce((s1, s2) => s1 + "\n" + s2) + "\n"
    else
      "\n"
  }
}

def genParamsToProto(actions : Seq[P4InfoAction]) : RIO[Unit, String] = for {
  paramCases <- mapM(actions, a => {
    a.preamble.fold
      (ifEmpty = ZIO.fail(new Exception("Failure: Action has empty preamble.")))
      (preamble => {
        val paramsIndexed = a.params.zipWithIndex
        if paramsIndexed.size > 0 then
          val paramTypes = a.params
            .map(p => "(\"" + p.name + "\", ByteString)")
            .reduce((p1, p2) => p1 + ", " + p2)
          val paramCaseVars = paramsIndexed
            .map((p,idx) => "(\"" + p.name + "\", p" + idx + ")")
            .reduce((p1, p2) => p1 + ", " + p2)
          val paramCaseResult = paramsIndexed
            .map((p,idx) => "Seq(Param(paramId = " + p.id + ", value = p" + idx + "))")
            .reduce((p1, p2) => p1 + " ++ " + p2)
          ZIO.succeed("        case (\"" + preamble.name + "\", (" + paramCaseVars + ") : (" + paramTypes + ")) => " + paramCaseResult)
        else
          ZIO.succeed("        case (\"" + preamble.name + "\", _) => Seq.empty")
      })
  })
} yield {
  "    val params =\n" +
  "      (te.action, te.params) match\n" +
  "        case (\"*\", _) => Seq.empty\n" +
  {
    if paramCases.size > 0 then
      paramCases.reduce((s1, s2) => s1 + "\n" + s2) + "\n"
    else
      "\n"
  }
}

def genToProto(p4info : P4Info) : RIO[Unit, String] = for {
  table <- genTableToProto(p4info.tables)
  matchFields <- genMatchFieldsToProto(p4info.tables)
  action <- genActionToProto(p4info.actions)
  params <- genParamsToProto(p4info.actions)
} yield {
  "  override def toProto(te : p4rtype.TableEntry[TableMatchFields, TableAction, ActionParams, _, _]) : TableEntry =\n" +
  table + "\n" +
  matchFields + "\n" +
  action + "\n" +
  params + "\n" +
  "    TableEntry(\n" +
  "    tableId = tableId,\n" +
  "    `match` = matchFields,\n" +
  "    action =\n" +
  "      if actionId != 0 then\n" +
  "        Some(TableAction(\n" +
  "          `type` = TableAction.Type.Action(\n" +
  "            value = Action(\n" +
  "              actionId = actionId,\n" +
  "              params = params\n" +
  "            )\n" +
  "          )\n" +
  "        ))\n" +
  "      else\n" +
  "        None\n" +
  "  )\n"
}

def genTableFromProto(tables : Seq[Table]) : RIO[Unit, String] = for {
  tableCases <- mapM[Table, String](tables, t => {
    t.preamble.fold
      (ifEmpty = ZIO.fail(new Exception("Failure: Table has empty preamble.")))
      (preamble => ZIO.succeed("        case " + preamble.id + " => \"" + preamble.name + "\""))
  })
} yield {
  "    val table =\n" +
  "      te.tableId match\n" +
  {
    if tableCases.size > 0 then
      tableCases.reduce((c1, c2) => c1 + "\n" + c2) + "\n"
    else
      "\n"
  } +
  "        case 0 => \"*\""
}

def genMatchFieldFromProtoCase(tableId : Int, mfs : Seq[MatchField]) : RIO[Unit, String] = for {
  matchFields <- mapM(mfs, mf => {
    mf.`match`.matchType.get match
      case EXACT    => ZIO.succeed("te.`match`.find(_.fieldId == " + mf.id + ").map(fm => (\"" + mf.name + "\", Exact(fm.fieldMatchType.exact.get.value))).get")
      case LPM      => ZIO.succeed("te.`match`.find(_.fieldId == " + mf.id + ").map(fm => (\"" + mf.name + "\", LPM(fm.fieldMatchType.lpm.get.value, fm.fieldMatchType.lpm.get.prefixLen)))")
      case RANGE    => ZIO.succeed("te.`match`.find(_.fieldId == " + mf.id + ").map(fm => (\"" + mf.name + "\", Range(fm.fieldMatchType.range.get.low, fm.fieldMatchType.range.get.high)))")
      case TERNARY  => ZIO.succeed("te.`match`.find(_.fieldId == " + mf.id + ").map(fm => (\"" + mf.name + "\", Ternary(fm.fieldMatchType.ternary.get.value, fm.fieldMatchType.ternary.get.mask)))")
      case OPTIONAL => ZIO.succeed("te.`match`.find(_.fieldId == " + mf.id + ").map(fm => (\"" + mf.name + "\", Optional(fm.fieldMatchType.optional.get.value)))")
      case _ => ZIO.fail(new Exception("Failure: Unknown match type."))
  })
} yield {
  if matchFields.size > 0 then
    "        case " + tableId + " => (" + matchFields.reduce((s1, s2) => s1 + ", " + s2) + ")"
  else
    "        case " + tableId + " => ()"
}

def genMatchFieldsFromProto(tables : Seq[Table]) : RIO[Unit, String] = for {
  tableMatches <- mapM[Table, String](tables, t => {
    t.preamble.fold
      (ifEmpty = ZIO.fail(new Exception("Failure: Table has empty preamble.")))
      (preamble => genMatchFieldFromProtoCase(preamble.id, t.matchFields))
  })
} yield {
  "    val matches =\n" +
  "      te.tableId match\n" +
  {
    if tableMatches.size > 0 then
      tableMatches.reduce((s1, s2) => s1 + "\n" + s2) + "\n"
    else
      "\n"
  } +
  "        case 0 => \"*\""
}

def genActionFromProto(actions : Seq[P4InfoAction]) : RIO[Unit, String] = for {
  actionCases <- mapM(actions, a => {
    a.preamble.fold
      (ifEmpty = ZIO.fail(new Exception("Failure: Action has empty preamble.")))
      (preamble => ZIO.succeed("        case " + preamble.id + " => \"" + preamble.name + "\""))
  })
} yield {
  "    val action =\n" +
  "      actionId match\n" +
  {
    if actionCases.size > 0 then
      actionCases.reduce((s1, s2) => s1 + "\n" + s2) + "\n"
    else
      "\n"
  } +
  "        case 0 => \"*\""
}

def genParamsFromProtoCase(actionId : Int, prms : Seq[Param]) : RIO[Unit, String] = for {
  params <- mapM(prms, p => ZIO.succeed(
      "teParams.find(_.paramId == " + p.id + ").map(pm => (\"" + p.name + "\", pm.value)).get"
  ))
} yield {
  if params.size > 0 then
    "        case " + actionId + " => (" + params.reduce((s1, s2) => s1 + ", " + s2) + ")"
  else
    "        case " + actionId + " => ()"
}

def genParamsFromProto(actions : Seq[P4InfoAction]) : RIO[Unit, String] = for {
  actionMatches <- mapM(actions, a => {
    a.preamble.fold
      (ifEmpty = ZIO.fail(new Exception("Failure: Action has empty preamble.")))
      (preamble => genParamsFromProtoCase(preamble.id, a.params))
  })
} yield {
  "    val params =\n" +
  "      actionId match\n" +
  {
    if actionMatches.size > 0 then
      actionMatches.reduce((s1, s2) => s1 + "\n" + s2) + "\n"
    else
      "\n"
  } +
  "        case 0 => \"*\""
}

def genFromProto(p4info : P4Info) : RIO[Unit, String] = for {
  table <- genTableFromProto(p4info.tables)
  matchFields <- genMatchFieldsFromProto(p4info.tables)
  action <- genActionFromProto(p4info.actions)
  params <- genParamsFromProto(p4info.actions)
} yield {
  "  override def fromProto[TM[_], TA[_], TP[_], XN <: String, XA <: TA[XN]](te : TableEntry): p4rtype.TableEntry[TM, TA, TP, XN, XA] =\n" +
  "    val actionId = te.action.get.`type`.action.get.actionId\n" +
  "    val teParams = te.action.get.`type`.action.get.params\n\n" +
  table + "\n" +
  matchFields + "\n" +
  action + "\n" +
  params + "\n" +
  "    val myTable : XN = table.asInstanceOf[XN]\n" +
  "    val myAction : TA[myTable.type] = action.asInstanceOf[TA[myTable.type]]\n" +
  "    p4rtype.TableEntry[TM, TA, TP](\n" +
  "      table = myTable,\n" +
  "      matches = matches.asInstanceOf[TM[myTable.type]],\n" +
  "      action = myAction,\n" +
  "      params = params.asInstanceOf[TP[myAction.type]],\n" +
  "      1\n" +
  "    ).asInstanceOf[p4rtype.TableEntry[TM, TA, TP, XN, XA]]\n"
}

def genChannel(p4info : P4Info) : RIO[Unit, String] = for {
  toProto <- genToProto(p4info)
  fromProto <- genFromProto(p4info)
} yield {
  "class Chan (deviceId : Int, socket : P4RuntimeStub, channel : io.grpc.ManagedChannel) extends p4rtype.Chan[TableMatchFields, TableAction, ActionParams](deviceId, socket, channel):\n" +
  toProto + "\n" +
  fromProto
}
// === `connect` API ===
def genConnect(p4info : P4Info) : RIO[Unit, String] = ZIO.succeed(
  "/** Connect to a P4Runtime server.\n" +
  "  * @param id The device ID, which is assigned by the controller (i.e. the caller), and should be unique for each controller.\n" +
  "  * @param ip IP address of the target device.\n" +
  "  * @param port Port number of the target device.\n" +
  "  * @return A `Chan` object used by the other P4R-Type API functions for communication.\n" +
  "  */\n" +
  "def connect(id : Int, ip : String, port : Int) : Chan =\n" +
  "  val channel = ManagedChannelBuilder.forAddress(ip, port).usePlaintext().build()\n" +
  "  val request = StreamMessageRequest(\n" +
  "      StreamMessageRequest.Update.Arbitration(\n" +
  "        value = MasterArbitrationUpdate(\n" +
  "          deviceId = id,\n" +
  "          electionId = Some(Uint128(high=0,low=1)),\n" +
  "        )\n" +
  "      )\n" +
  "    )\n" +
  "  val stub = P4RuntimeStub.newStub(channel, CallOptions.DEFAULT)\n" +
  "  val response_obs = new P4RTypeRuntimeObserver[StreamMessageResponse](StreamMessageResponse())\n" +
  "  val request_obs = stub.streamChannel(response_obs)\n" +
  "  request_obs.onNext(request)\n" +
  "  Chan(id, stub, channel)\n\n"
)

def genP4Info(p4info : P4Info) : RIO[Unit, String] =
  for {
    imports <- genImports()
    matchTypes <- genMatchTypes(p4info)
    channel <- genChannel(p4info)
    connect <- genConnect(p4info)
  } yield {
    imports + "\n\n" +
    matchTypes + "\n" +
    channel + "\n" +
    connect + "\n"
  }

object parseP4info extends ZIOAppDefault {
  def run =
    val layer : ZLayer[Any, Throwable, Unit] = ZLayer.succeed(())
    layer { for {
      args <- getArgs
      p4info <- {
        if args.length == 2 then
          val lines = fromFile(System.getProperty("user.dir") + "/" + args(0)).mkString
          val parser = scalapb.json4s.Parser()
          val p4info : P4Info = parser.fromJsonString[P4Info](lines)
          ZIO.succeed(p4info)
        else
          ZIO.fail(new Exception("usage: sbt \"runMain parseP4info <p4info.json> <package-name>\""))
      }
      output <- genP4Info(p4info)
      _ <- ZStream
        .fromIterable("package " + args(1) + "\n\n" + output)
        .run[Any, Throwable, Unit](
          ZSink
            .fromOutputStream(java.lang.System.out)
            .contramap[Char](_.toByte)
            .as(())
        )
    } yield () }
}