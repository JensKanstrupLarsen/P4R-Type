package config2_new

import p4rtype.{Exact, LPM, Optional, Range, Ternary, P4RTypeRuntimeObserver}
import com.google.protobuf.ByteString
import p4.v1.p4runtime.FieldMatch
import p4.v1.p4runtime.FieldMatch.FieldMatchType
import p4.v1.p4runtime.TableAction
import p4.v1.p4runtime.Action
import p4.v1.p4runtime.P4RuntimeGrpc.P4RuntimeStub
import p4.v1.p4runtime.TableEntry
import io.grpc.ManagedChannelBuilder
import p4.v1.p4runtime.StreamMessageRequest
import p4.v1.p4runtime.MasterArbitrationUpdate
import p4.v1.p4runtime.Uint128
import io.grpc.CallOptions
import p4.v1.p4runtime.StreamMessageResponse
import p4.v1.p4runtime.Action.Param

type TableMatchFields[TN] =
  TN match
    case "Process.firewall" => (Option[("hdr.ipv4.dstAddr", LPM)]) | "*"
    case "Process.ipv4_table" => (Option[("hdr.ipv4.dstAddr", LPM)]) | "*"
    case "*" => "*"
type ActionName = "NoAction" | "Process.drop" | "Process.forward_packet" | "*"

type TableAction[TN] <: ActionName =
  TN match
    case "Process.firewall" => "Process.drop" | "NoAction" | "*"
    case "Process.ipv4_table" => "Process.forward_packet" | "Process.drop" | "*"
    case "*" => "*"

type ActionParams[AN] =
  AN match
    case "NoAction" => Unit
    case "Process.drop" => Unit
    case "Process.forward_packet" => (("dstAddr", ByteString), ("port", ByteString))
    case "*" => "*"

class Chan (deviceId : Int, socket : P4RuntimeStub, channel : io.grpc.ManagedChannel) extends p4rtype.Chan[TableMatchFields, TableAction, ActionParams](deviceId, socket, channel):
  override def toProto(te : p4rtype.TableEntry[TableMatchFields, TableAction, ActionParams, _, _]) : TableEntry =
    val tableId =
      te.table match
        case "*" => 0
        case "Process.firewall" => 43479776
        case "Process.ipv4_table" => 39373426

    val matchFields =
      (te.table, te.matches) match
        case ("*", _) => Seq.empty
        case (_, _ : "*") => Seq.empty
        case ("Process.firewall", (t0)) => t0.asInstanceOf[Option[("hdr.ipv4.dstAddr", LPM)]].map((_, t) => p4rtype.matchFieldToProto(1, t)).toSeq
        case ("Process.ipv4_table", (t0)) => t0.asInstanceOf[Option[("hdr.ipv4.dstAddr", LPM)]].map((_, t) => p4rtype.matchFieldToProto(1, t)).toSeq

    val actionId =
      te.action match
        case "*" => 0
        case "NoAction" => 21257015
        case "Process.drop" => 22338797
        case "Process.forward_packet" => 22361362

    val params =
      (te.action, te.params) match
        case ("*", _) => Seq.empty
        case ("NoAction", _) => Seq.empty
        case ("Process.drop", _) => Seq.empty
        case ("Process.forward_packet", (("dstAddr", p0), ("port", p1)) : (("dstAddr", ByteString), ("port", ByteString))) => Seq(Param(paramId = 1, value = p0)) ++ Seq(Param(paramId = 2, value = p1))

    TableEntry(
    tableId = tableId,
    `match` = matchFields,
    action =
      if actionId != 0 then
        Some(TableAction(
          `type` = TableAction.Type.Action(
            value = Action(
              actionId = actionId,
              params = params
            )
          )
        ))
      else
        None
  )

  override def fromProto[TM[_], TA[_], TP[_], XN <: String, XA <: TA[XN]](te : TableEntry): p4rtype.TableEntry[TM, TA, TP, XN, XA] =
    val actionId = te.action.get.`type`.action.get.actionId
    val teParams = te.action.get.`type`.action.get.params

    val table =
      te.tableId match
        case 43479776 => "Process.firewall"
        case 39373426 => "Process.ipv4_table"
        case 0 => "*"
    val matches =
      te.tableId match
        case 43479776 => (te.`match`.find(_.fieldId == 1).map(fm => ("hdr.ipv4.dstAddr", LPM(fm.fieldMatchType.lpm.get.value, fm.fieldMatchType.lpm.get.prefixLen))))
        case 39373426 => (te.`match`.find(_.fieldId == 1).map(fm => ("hdr.ipv4.dstAddr", LPM(fm.fieldMatchType.lpm.get.value, fm.fieldMatchType.lpm.get.prefixLen))))
        case 0 => "*"
    val action =
      actionId match
        case 21257015 => "NoAction"
        case 22338797 => "Process.drop"
        case 22361362 => "Process.forward_packet"
        case 0 => "*"
    val params =
      actionId match
        case 21257015 => ()
        case 22338797 => ()
        case 22361362 => (teParams.find(_.paramId == 1).map(pm => ("dstAddr", pm.value)).get, teParams.find(_.paramId == 2).map(pm => ("port", pm.value)).get)
        case 0 => "*"
    val myTable : XN = table.asInstanceOf[XN]
    val myAction : TA[myTable.type] = action.asInstanceOf[TA[myTable.type]]
    p4rtype.TableEntry[TM, TA, TP](
      table = myTable,
      matches = matches.asInstanceOf[TM[myTable.type]],
      action = myAction,
      params = params.asInstanceOf[TP[myAction.type]],
      1
    ).asInstanceOf[p4rtype.TableEntry[TM, TA, TP, XN, XA]]

/**
 * Connect to a P4Runtime server.
 * @param id The device ID, which is assigned by the controller (i.e. the caller), and should be unique for each controller.
 * @param ip IP address of the target device.
 * @param port Port number of the target device.
 * @return A `Chan` object used by the other P4R-Type API functions for communication.
 */
def connect(id : Int, ip : String, port : Int) : Chan =
  val channel = ManagedChannelBuilder.forAddress(ip, port).usePlaintext().build()
  val request = StreamMessageRequest(
      StreamMessageRequest.Update.Arbitration(
        value = MasterArbitrationUpdate(
          deviceId = id,
          electionId = Some(Uint128(high=0,low=1)),
        )
      )
    )
  val stub = P4RuntimeStub.newStub(channel, CallOptions.DEFAULT)
  val response_obs = new P4RTypeRuntimeObserver[StreamMessageResponse](StreamMessageResponse())
  val request_obs = stub.streamChannel(response_obs)
  request_obs.onNext(request)
  Chan(id, stub, channel)
