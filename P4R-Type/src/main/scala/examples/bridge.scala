import p4rtype._
import com.google.protobuf.ByteString
import scala.annotation.switch

@main def bridge() =
  val s1 = config1.connect(0, "127.0.0.1", 50051)
  val s2 = config1.connect(1, "127.0.0.1", 50052)
  val s3 = config2_new.connect(2, "127.0.0.1", 50053)
  val s4 = config2_new.connect(3, "127.0.0.1", 50054)

  for ((ip,mac,port) <- List((bytes(10,0,1,1), bytes(8,0,0,0,1,17), bytes(1)),
                             (bytes(10,0,2,2), bytes(8,0,0,0,2,34), bytes(2)),
                             (bytes(10,0,3,3), bytes(8,0,0,0,3,51), bytes(3)),
                             (bytes(10,0,4,4), bytes(8,0,0,0,4,68), bytes(4))))
    insert(s1, TableEntry(
      "Process.ipv4_lpm",
      Some("hdr.ipv4.dstAddr", LPM(ip, 32)),
      "Process.ipv4_forward",
      (("dstAddr", mac), ("port", port)), 1
    ))
    insert(s3, TableEntry(
      "Process.ipv4_table",
      Some("hdr.ipv4.dstAddr", LPM(ip, 32)),
      "Process.forward_packet",
      (("dstAddr", mac), ("port", port)), 1
    ))

  val s1_all_entries = read(s1,TableEntry("Process.ipv4_lpm", "*", "*", "*", 0))
  for (entry <- s1_all_entries) yield
    insert(s2, entry)

  val s3_all_entries = read(s3,TableEntry("Process.ipv4_table", "*", "*", "*", 0))
  for (entry <- s3_all_entries) yield
    insert(s4, entry)

  s1.disconnect()
  s2.disconnect()
  s3.disconnect()
  s4.disconnect()
