import p4rtype._
import com.google.protobuf.ByteString
import scala.annotation.switch

@main def loadbalancer() =
  // Fully connect all hosts
  bridge()

  val s1 = config1_lb.connect(0, "127.0.0.1", 50051)
  var time = 0

  while(time < 60) {
    val p2c =
      readCounterEntry(s1,CounterEntry(0,Some(2),None)).head match
        case CounterEntry(_,_,Some((_,p))) => p
        case _ => 0
    val p3c =
      readCounterEntry(s1,CounterEntry(0,Some(3),None)).head match
        case CounterEntry(_,_,Some((_,p))) => p
        case _ => 0
    val p4c =
      readCounterEntry(s1,CounterEntry(0,Some(4),None)).head match
        case CounterEntry(_,_,Some((_,p))) => p
        case _ => 0
    println("time: " + time + "s | Outgoing packets on ports [2]:" + p2c + " [3]:" + p3c + " [4]:" + p4c)

    if (p4c < p2c && p4c < p3c) {
      modify(s1,TableEntry(
        "Process.ipv4_lpm",
        Some("hdr.ipv4.dstAddr", LPM(bytes(10,0,4,4), 32)),
        "Process.ipv4_forward",
        (("dstAddr", bytes(8,0,0,0,4,68)), ("port", bytes(4))), 1
      ))
      println("Routing through port 4")
    } else if (p2c < p3c) {
      modify(s1,TableEntry(
        "Process.ipv4_lpm",
        Some("hdr.ipv4.dstAddr", LPM(bytes(10,0,4,4), 32)),
        "Process.ipv4_forward",
        (("dstAddr", bytes(8,0,0,0,2,34)), ("port", bytes(2))), 1
      ))
      println("Routing through port 2")
    } else {
      modify(s1,TableEntry(
        "Process.ipv4_lpm",
        Some("hdr.ipv4.dstAddr", LPM(bytes(10,0,4,4), 32)),
        "Process.ipv4_forward",
        (("dstAddr", bytes(8,0,0,0,3,51)), ("port", bytes(3))), 1
      ))
      println("Routing through port 3")
    }
    Thread.sleep(5000)
    time += 5
  }

  s1.disconnect()
