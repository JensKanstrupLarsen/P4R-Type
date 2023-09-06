import p4rtype._
import com.google.protobuf.ByteString
import scala.annotation.switch

@main def forward_c1() =
  val s1 = config1.connect(0, "127.0.0.1", 50051)
  val s2 = config1.connect(1, "127.0.0.1", 50052)

  insert(s1, TableEntry(
    "Process.ipv4_lpm",
    Some("hdr.ipv4.dstAddr", LPM(bytes(10,0,1,1), 32)),
    "Process.ipv4_forward",
    (("dstAddr", bytes(8,0,0,0,1,17)), ("port", bytes(1))), 1
  ))

  /* This code uses a "wrong" action, which will cause it to not compile.
  insert(s1, TableEntry(
    "Process.ipv4_lpm",
    Some("hdr.ipv4.dstAddr", LPM(bytes(10,0,1,1), 32)),
    "Process.NoAction", // <-- This action exists, but is incompatible with the ipv4_lpm table.
    (), 1
  ))
  */

  insert(s1, TableEntry(
    "Process.ipv4_lpm",
    Some("hdr.ipv4.dstAddr", LPM(bytes(10,0,2,2), 32)),
    "Process.ipv4_forward",
    (("dstAddr", bytes(8,0,0,0,2,34)), ("port", bytes(2))), 1
  ))

  val c1_ipv4_entries = read(s1,TableEntry("*", "*", "*", "*", 0))
  for (entry <- c1_ipv4_entries) yield
    entry.table match
      case "Process.ipv4_lpm" => insert(s2, entry)
      case _ => true

  s1.disconnect()
  s2.disconnect()
