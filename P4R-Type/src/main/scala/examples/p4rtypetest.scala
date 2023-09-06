import p4rtype._
import com.google.protobuf.ByteString
import scala.annotation.switch

@main def p4rtypeTest() : Int =
  val s1 = config1.connect(0, "127.0.0.1", 50051)

  insert(s1, TableEntry(
    "Process.ipv4_lpm",
    Some("hdr.ipv4.dstAddr", LPM(bytes(10,0,1,1), 32)),
    "Process.ipv4_forward",
    (("dstAddr", bytes(8,0,0,0,1,17)), ("port", bytes(1))), 1
  ))

  /* This table entry insertion does not compile
  insert(s1, TableEntry(
    "Process.ipv4_lpm",
    Some("hdr.ipv4.dstAddr", LPM(bytes(10,0,1,1), 32)),
    "Process.ipv6_forward", // <-- Will cause an error
    (("dstAddr", bytes(8,0,0,0,1,17)), ("port", bytes(1))), 1
  ))
  */

  var c1_ipv4_entries = read(s1,TableEntry("*", "*", "*", "*", 0))

  if (c1_ipv4_entries.size != 1) {
    println("Error: expected 1 entry in table, found " + c1_ipv4_entries.size)
    return 1
  }

  delete(s1, TableEntry(
    "Process.ipv4_lpm",
    Some("hdr.ipv4.dstAddr", LPM(bytes(10,0,1,1), 32)),
    "Process.ipv4_forward",
    (("dstAddr", bytes(8,0,0,0,1,17)), ("port", bytes(1))), 1
  ))

  c1_ipv4_entries = read(s1,TableEntry("*", "*", "*", "*", 0))

  if (c1_ipv4_entries.size != 0) {
    println("Error: expected 0 entries in table, found " + c1_ipv4_entries.size)
    return 1
  }

  s1.disconnect()

  println("Test successful!")
  return 0
