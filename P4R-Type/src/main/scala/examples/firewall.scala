import p4rtype._
import com.google.protobuf.ByteString
import scala.annotation.switch

@main def firewall() =
  // Fully connect all hosts
  bridge()

  val s1 = config1.connect(0, "127.0.0.1", 50051)
  val s2 = config1.connect(1, "127.0.0.1", 50052)
  val s3 = config2.connect(2, "127.0.0.1", 50053)
  val s4 = config2.connect(3, "127.0.0.1", 50054)

  // Writing firewall entries
  for (s <- List(s1, s2, s3, s4))
    for (ip <- List(bytes(10,0,1,1), bytes(10,0,4,4)))
      insert(s, TableEntry(
        "Process.firewall",
        Some("hdr.ipv4.dstAddr", LPM(ip, 32)),
        "Process.drop", (), 1
      ))

  // Reading C1 ipv4 entries
  val c1_ipv4_entries = read(s1,
    TableEntry("Process.ipv4_lpm", "*", "*", "*", 0)
  )
  // Writing C1 ipv4 entries
  for (entry <- c1_ipv4_entries)
    insert(s2, entry)

  // Reading C2 ipv4 entries
  val c2_ipv4_entries = read(s3,
    TableEntry("Process.ipv4_table", "*", "*", "*", 0)
  )
  // Writing C2 ipv4 entries
  for (entry <- c2_ipv4_entries)
    insert(s4, entry)

  s1.disconnect()
  s2.disconnect()
  s3.disconnect()
  s4.disconnect()