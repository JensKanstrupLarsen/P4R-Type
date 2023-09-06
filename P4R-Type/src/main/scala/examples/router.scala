import p4rtype._
import com.google.protobuf.ByteString
import scala.annotation.switch

def ipToString(ip: ByteString) =
  ip.toByteArray().map(b => b.toInt.toString()).mkString(".")

def portToString(port: ByteString) =
  val port_arr = port.toByteArray()
  if port_arr.length == 1
  then port_arr(0).toInt.toString()
  else ((((port_arr(0)+256)%256) << 8) + ((port_arr(1)+256)%256)).toString()

def getRule() : Option[(ByteString, ByteString, ByteString, ByteString)] =
  val extIP   = ByteString.copyFrom(scala.io.StdIn.readLine("External IPv4 address: ").split('.').map(s => s.toByte))
  val extPort_int = scala.io.StdIn.readLine("External port: ").toInt
  val extPort = bytes(((extPort_int >> 8) & 0xFF).toByte, (extPort_int & 0xFF).toByte)
  val intIP_s =
    scala.io.StdIn.readLine("Internal host [1/2/3]: ").toIntOption match
      case Some(1) => Some(bytes(10,0,1,1))
      case Some(2) => Some(bytes(10,0,2,2))
      case Some(3) => Some(bytes(10,0,3,3))
      case _ => None
  val intPort = bytes(31,-112) // 8080
  intIP_s match
    case Some(intIP) => Some((extIP, extPort, intIP, intPort))
    case _ => None

@main def router() =
  // Fully connect all hostsW
  bridge()
  Thread.sleep(250)

  val s4 = config2_nat.connect(3, "127.0.0.1", 50054)

  var break = false

  while(!break) {
    val act = scala.io.StdIn.readLine("Would you like to [r]ead, [a]dd or [d]elete rules (or [q]uit)? ")
    act match
      case "r" =>
        val ins = read(s4, TableEntry("Process.nat_ingress", "*", "*", "*", 0))
        println("Ingress rules:")
        for (i <- ins)
          i match
            case TableEntry(
              "Process.nat_ingress",
              (Some(("hdr.ipv4.dstAddr", LPM(extIP,_))), ("hdr.tcp.dstPort", Exact(extPort))),
              "Process.nat_translate_in",
              (("dstAddr", intIP : ByteString), ("dstPort", intPort : ByteString)),
              _
            ) =>
              println("(" + ipToString(extIP) + ":" + portToString(extPort) + ") -> (" + ipToString(intIP) + ":" + portToString(intPort) + ")")
            case _ => println("Error: unexpected table entry")
        val egs = read(s4, TableEntry("Process.nat_egress", "*", "*", "*", 0))
        println("Egress rules:")
        for (e <- egs)
          e match
            case TableEntry(
              "Process.nat_egress",
              (Some(("hdr.ipv4.srcAddr", LPM(intIP,_))), ("hdr.tcp.srcPort", Exact(intPort))),
              "Process.nat_translate_eg",
              (("srcAddr", extIP : ByteString), ("srcPort", extPort : ByteString)),
              _
            ) =>
              println("(" + ipToString(intIP) + ":" + portToString(intPort) + ") -> (" + ipToString(extIP) + ":" + portToString(extPort) + ")")
            case _ => println("Error: unexpected table entry")

      case "a" =>
        val ruleAddresses = getRule()
        ruleAddresses match
          case None => println("Invalid address/port/host.")
          case Some((extIP, extPort, intIP, intPort)) =>
            val in_res = insert(s4,TableEntry(
              "Process.nat_ingress",
              (Some("hdr.ipv4.dstAddr", LPM(extIP, 32)), ("hdr.tcp.dstPort", Exact(extPort))),
              "Process.nat_translate_in",
              (("dstAddr", intIP), ("dstPort", intPort)), 0
            ))
            val eg_res = insert(s4,TableEntry(
              "Process.nat_egress",
              (Some("hdr.ipv4.srcAddr", LPM(intIP, 32)), ("hdr.tcp.srcPort", Exact(intPort))),
              "Process.nat_translate_eg",
              (("srcAddr", extIP), ("srcPort", extPort)), 0
            ))
            if (in_res && eg_res) println("Successfully added rule.")
            else println("Failed to add rule.")

      case "d" =>
        val ruleAddresses = getRule()
        ruleAddresses match
          case None => println("Invalid address/port/host.")
          case Some((extIP, extPort, intIP, intPort)) =>
            val in_res = delete(s4,TableEntry(
              "Process.nat_ingress",
              (Some("hdr.ipv4.dstAddr", LPM(extIP, 32)), ("hdr.tcp.dstPort", Exact(extPort))),
              "*", "*", 0
            ))
            val eg_res = delete(s4,TableEntry(
              "Process.nat_egress",
              (Some("hdr.ipv4.srcAddr", LPM(intIP, 32)), ("hdr.tcp.srcPort", Exact(intPort))),
              "*", "*", 0
            ))
            if (in_res && eg_res) println("Successfully deleted rule.")
            else println("Failed to delete rule.")

      case "q" =>
        break = true

      case _ =>
        println("Invalid action.")
  }

  s4.disconnect()
