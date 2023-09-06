#!/usr/bin/env python3
# Derived from https://github.com/p4lang/tutorials/blob/master/exercises/basic_tunnel/receive.py
import os
import sys

from scapy.all import TCP, sniff

def handle_pkt(pkt):
    if TCP in pkt and pkt[TCP].dport == 8080:
        print("received packet from " + pkt[1].src + ":" + str(pkt[TCP].sport) + " with message " + str(pkt[TCP].payload.load))
        sys.stdout.flush()


def main():
    ifaces = [i for i in os.listdir('/sys/class/net/') if 'eth' in i]
    iface = ifaces[0]
    print("sniffing on %s" % iface)
    sys.stdout.flush()
    sniff(iface = iface,
          prn = lambda x: handle_pkt(x))

if __name__ == '__main__':
    main()