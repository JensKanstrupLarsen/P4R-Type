#!/usr/bin/env python3
# Derived from https://github.com/p4lang/tutorials/blob/master/exercises/basic_tunnel/send.py
import argparse
import random
import socket

from scapy.all import IP, TCP, Ether, get_if_hwaddr, get_if_list, sendp

def get_if():
    iface=None
    for i in get_if_list():
        if "eth0" in i:
            iface=i
            break;
    if not iface:
        print("Cannot find eth0 interface")
        exit(1)
    return iface

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('ip_addr', type=str, help="The destination IP address to use")
    parser.add_argument('port', type=int, help="The destination port to use")
    parser.add_argument('message', type=str, help="The message to include in packet")
    args = parser.parse_args()

    addr = socket.gethostbyname(args.ip_addr)
    iface = get_if()

    print("sending on interface {} to IP addr {}".format(iface, str(addr)))
    pkt =  Ether(src=get_if_hwaddr(iface), dst='ff:ff:ff:ff:ff:ff')
    pkt = pkt / IP(dst=addr) / TCP(dport=args.port, sport=8080) / args.message

    # pkt.show2()
    sendp(pkt, iface=iface, verbose=False)


if __name__ == '__main__':
    main()