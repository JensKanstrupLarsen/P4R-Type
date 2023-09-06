import p4rtype._
import com.google.protobuf.ByteString
import scala.annotation.switch

@main def forward_c2() =
  val s3 = config2_new.connect(2, "127.0.0.1", 50053)
  val s4 = config2_new.connect(3, "127.0.0.1", 50054)

  insert(s3, TableEntry(
    "Process.ipv4_table",
    Some("hdr.ipv4.dstAddr", LPM(bytes(10,0,3,3), 32)),
    "Process.forward_packet",
    (("dstAddr", bytes(8,0,0,0,3,51)), ("port", bytes(3))), 1
  ))

  insert(s3, TableEntry(
    "Process.ipv4_table",
    Some("hdr.ipv4.dstAddr", LPM(bytes(10,0,4,4), 32)),
    "Process.forward_packet",
    (("dstAddr", bytes(8,0,0,0,4,68)), ("port", bytes(4))), 1
  ))

  val c2_ipv4_entries = read(s3,TableEntry("*", "*", "*", "*", 0))
  for (entry <- c2_ipv4_entries) yield
    entry.table match
      case "Process.ipv4_table" => insert(s4, entry)
      case _ => true

  s3.disconnect()
  s4.disconnect()