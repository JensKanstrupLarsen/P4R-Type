package p4rtype

import p4.v1.p4runtime.FieldMatch
import p4.v1.p4runtime.TableAction
import com.google.protobuf.ByteString
import p4.v1.p4runtime.FieldMatch.FieldMatchType
import p4.v1.p4runtime.Action
import p4.v1.p4runtime.Action.Param
import p4.v1.p4runtime.P4RuntimeGrpc.P4RuntimeStub
import io.grpc.stub.StreamObserver
import p4.v1.p4runtime.ReadRequest
import p4.v1.p4runtime.ReadResponse
import p4.v1.p4runtime.Entity
import p4.v1.p4runtime.WriteRequest
import p4.v1.p4runtime.Update
import p4.v1.p4runtime.Uint128
import scala.concurrent.Await
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration.SECONDS
import io.grpc.ManagedChannel

type Wildcard = "*"

case class Exact(v : ByteString)
case class LPM(v : ByteString, pl : Int)
case class Range(l : ByteString, h : ByteString)
case class Ternary(v : ByteString, m : ByteString)
case class Optional(v : ByteString)

type MatchFieldType = Exact | LPM | Range | Ternary | Optional

def matchFieldToProto(fieldId : Int, mf : MatchFieldType) : FieldMatch =
  mf match
    case Exact(v)      => FieldMatch(fieldId, FieldMatchType.Exact(FieldMatch.Exact(value = v)))
    case LPM(v, pl)    => FieldMatch(fieldId, FieldMatchType.Lpm(FieldMatch.LPM(value = v, prefixLen = pl)))
    case Range(l, h)   => FieldMatch(fieldId, FieldMatchType.Range(FieldMatch.Range(low = l, high = h)))
    case Ternary(v, m) => FieldMatch(fieldId, FieldMatchType.Ternary(FieldMatch.Ternary(value = v, mask = m)))
    case Optional(v)   => FieldMatch(fieldId, FieldMatchType.Optional(FieldMatch.Optional(value = v)))

case class TableEntry [TM[_], TA[_], TP[_], XN, XA <: TA[XN]] private (table : XN, matches : TM[XN], action : XA, params : TP[XA], priority : Int)

/** Represents a table entry in a control plane table.
  */
object TableEntry:
  def apply[TM[_], TA[_], TP[_]] (table : String, matches : TM[table.type], action : TA[table.type], params : TP[action.type], priority : Int) : TableEntry[TM, TA, TP, table.type, action.type] =
    TableEntry[TM, TA, TP, table.type, action.type](table, matches, action, params, priority)

case class CounterEntry (counter_id : Int, index : Option[Long], data : Option[(Long, Long)])

type P4Entity[TM[_], TA[_], TP[_], XN, XA <: TA[XN]] = TableEntry[TM, TA, TP, XN, XA] | CounterEntry

/** Represents a connection channel to a target device.
  */
abstract class Chan[TM[_], TA[_], TP[_]] (deviceId : Int, socket : P4RuntimeStub, channel : ManagedChannel):
  /** Returns the device ID of the target device.
    * This is assigned by the controller (i.e. the caller),
    * and should be unique for each controller.
    */
  def getDeviceId() : Int = deviceId
  /** Returns the socket used to communicate with the target device.
    */
  def getSocket() : P4RuntimeStub = socket
  /** Converts a `TableEntry` from the P4R-Type representation
    * to the underlying protobuf representation.
    */
  def toProto(te : TableEntry[TM, TA, TP, _, _]) : p4.v1.p4runtime.TableEntry
  /** Converts a `CounterEntry` from the P4R-Type representation
    * to the underlying protobuf representation.
    */
  def toProto(c : CounterEntry) : p4.v1.p4runtime.CounterEntry =
    p4.v1.p4runtime.CounterEntry(
      counterId = c.counter_id,
      index = c.index.map(l => p4.v1.p4runtime.Index(l)),
      data = c.data.map((byteCount, packetCount) => p4.v1.p4runtime.CounterData(byteCount, packetCount))
  )
  /** Converts a `TableEntry` from the underlying protobuf representation
    * to the P4R-Type representation.
    */
  def fromProto[TM[_], TA[_], TP[_], XN <: String, XA <: TA[XN]](te : p4.v1.p4runtime.TableEntry) : TableEntry[TM, TA, TP, XN, XA]
  /** Converts a `CounterEntry` from the underlying protobuf representation
    * to the P4R-Type representation.
    */
  def fromProto(c : p4.v1.p4runtime.CounterEntry) : CounterEntry =
  CounterEntry(
    counter_id = c.counterId,
    index = c.index.map(i => i.index),
    data = c.data.map(d => (d.byteCount, d.packetCount))
  )
  /** Disconnects the channel, shutting down the connection to the target.
    */
  def disconnect() : Unit =
    channel.shutdownNow()

class P4RTypeRuntimeObserver[O](lock : Object) extends StreamObserver[O] {
  private var log : Seq[O] = Seq()
  def getLog() : Seq[O] = log
  override def onNext(value: O): Unit =
    log = log :+ value
  override def onCompleted(): Unit =
    lock.synchronized {
      lock.notify()
    }
  override def onError(t: Throwable): Unit =
    print("[ERROR] " + t.toString() + "\n")
}

/** Internal function that reads by matching entities with a 'querying' entity.
  *
  * @param c The channel used to communicate with the target device.
  * @param entity The entity to match on. For details on how the matching works, see the P4Runtime specification.
  * @return A list of entities that match the given entity.
  */
private def readAny[TM[_], TA[_], TP[_]]
  (c : Chan[TM, TA, TP], entity : P4Entity[TM, TA, TP, _, _]) : Seq[P4Entity[TM, TA, TP, _, _]] =
  val lock = Object()
  val read_observer = new P4RTypeRuntimeObserver[p4.v1.p4runtime.ReadResponse](lock)
  c.getSocket().read(
    request = ReadRequest(
      deviceId = c.getDeviceId(),
      entities = List(
        Entity (
          entity match
            case te : TableEntry[TM, TA, TP, _, _] => Entity.Entity.TableEntry(c.toProto(te))
            case ce : CounterEntry => Entity.Entity.CounterEntry(c.toProto(ce))
        )
      )
    ),
    read_observer
  )
  lock.synchronized {
    lock.wait()
  }
  read_observer.getLog().last.entities.foldLeft(List[P4Entity[TM, TA, TP, _, _]]())((acc, e) => {
    e.entity match
      case Entity.Entity.TableEntry(te) => acc :+ c.fromProto(te)
      case Entity.Entity.CounterEntry(ce) => acc :+ c.fromProto(ce)
      case _ => acc
  })

/** Reads the contents of a table by matching entries with a given table entry.
  *
  * @param c The channel used to communicate with the target device.
  * @param entity The table entry to match on. For details on how the matching works, see the P4Runtime specification.
  * @return A list of table entries that match the given table entry.
  */
def read[TM[_], TA[_], TP[_]]
  (c : Chan[TM, TA, TP], entity : TableEntry[TM, TA, TP, _, _]) : Seq[TableEntry[TM, TA, TP, _, _]] =
  readAny(c, entity).asInstanceOf[Seq[TableEntry[TM, TA, TP, _, _]]]

/** Reads the contents of one or more counter entries.
  *
  * @param c The channel used to communicate with the target device.
  * @param entity The 'querying' counter entry to match on. For details on how the matching works, see the P4Runtime specification.
  * @return A list of counter entries that match the given entry.
  */
def readCounterEntry
  (c : Chan[_, _, _], entity : CounterEntry) : Seq[CounterEntry] =
  readAny(c, entity).asInstanceOf[Seq[CounterEntry]]

/** Writes a table entry to a table.
  * API users should not call this method directly, but instead use the `insert`, `modify`, and `delete` methods.
  *
  * @param c The channel used to communicate with the target device.
  * @param tableEntry The table entry to write.
  * @param ut The type of the update. See the P4Runtime specification for a list and description of update types.
  * @return A boolean indicating whether or not the write was successful (true if successful, false otherwise).
  */
def write [TM[_], TA[_], TP[_]]
  (c : Chan[TM, TA, TP], entity : P4Entity[TM, TA, TP, _, _], ut : Update.Type) : Boolean =
  val resp = c.getSocket().write(WriteRequest(
    deviceId = c.getDeviceId(),
    electionId = Some(Uint128(high=0,low=1)),
    updates = List(Update(
      `type` = ut,
      entity = Some(Entity(
        entity match
          case te : TableEntry[TM, TA, TP, _, _] => Entity.Entity.TableEntry(c.toProto(te))
          case ce : CounterEntry => Entity.Entity.CounterEntry(c.toProto(ce))
      ))
    ))
  ))
  try
    val response = Await.ready(resp, FiniteDuration(10, SECONDS))
    response.value match
      case Some(scala.util.Success(_)) =>
        true
      case _ =>
        false
  catch
    case _ =>
      false

/** Inserts a table entry into a table.
  *
  * @param c The channel used to communicate with the target device.
  * @param tableEntry The table entry to insert. For details on how the operation works, see the P4Runtime specification.
  * @return A boolean indicating whether or not the insertion was successful (true if successful, false otherwise).
  */
def insert [TM[_], TA[_], TP[_]]
  (c : Chan[TM, TA, TP], entity : P4Entity[TM, TA, TP, _, _]) : Boolean =
  write(c, entity, Update.Type.INSERT)

/** Modifies a table entry in a table.
  *
  * @param c The channel used to communicate with the target device.
  * @param tableEntry The table entry to modify. For details on how the operation works, see the P4Runtime specification.
  * @return A boolean indicating whether or not the modification was successful (true if successful, false otherwise).
  */
def modify [TM[_], TA[_], TP[_]]
  (c : Chan[TM, TA, TP], entity : P4Entity[TM, TA, TP, _, _]) : Boolean =
  write(c, entity, Update.Type.MODIFY)

/** Deletes a table entry from a table.
  *
  * @param c The channel used to communicate with the target device.
  * @param tableEntry The table entry to delete. For details on how the operation works, see the P4Runtime specification.
  * @return A boolean indicating whether or not the deletion was successful (true if successful, false otherwise).
  */
def delete [TM[_], TA[_], TP[_]]
  (c : Chan[TM, TA, TP], entity : P4Entity[TM, TA, TP, _, _]) : Boolean =
  write(c, entity, Update.Type.DELETE)

/** A helper function for generating bytestrings from sequences of bytes.
  *
  * @param a A sequence of bytes (which may be represented as integers).
  * @return A bytestring containing the given bytes.
  */
def bytes (a : Byte*) : ByteString = ByteString.copyFrom(a.toArray)
