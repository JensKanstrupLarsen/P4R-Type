#!/usr/bin/env python3
import argparse
import os
import sys
from time import sleep

import grpc

# Import P4Runtime lib from parent utils dir
# Probably there's a better way of doing this.
sys.path.append(
    os.path.join(os.path.dirname(os.path.abspath(__file__)),
                 'utils/'))
import p4runtime_lib.bmv2
import p4runtime_lib.helper
from p4runtime_lib.error_utils import printGrpcError
from p4runtime_lib.switch import ShutdownAllSwitchConnections

def setup_switch(p4info_file_path, bmv2_file_path, nm, ip_addr, dev_id, log_file):
    # Instantiate a P4Runtime helper from the p4info file
    p4info_helper = p4runtime_lib.helper.P4InfoHelper(p4info_file_path)

    # Create a switch connection object for s1 and s2;
    # this is backed by a P4Runtime gRPC connection.
    # Also, dump all P4Runtime messages sent to switch to given txt files.
    sw = p4runtime_lib.bmv2.Bmv2SwitchConnection(
        name=nm,
        address=ip_addr,
        device_id=dev_id,
        proto_dump_file=log_file)

    sw.MasterArbitrationUpdate()

    sw.SetForwardingPipelineConfig(p4info=p4info_helper.p4info,
                                   bmv2_json_file_path=bmv2_file_path)

    return (sw,p4info_helper)

if __name__ == '__main__':
    (s1,p4info1) = setup_switch(sys.argv[1]+".p4.p4info.txt",sys.argv[1]+".json","s1","127.0.0.1:50051",0,"./logs/s1_p4runtime_requests.txt")
    (s2,p4info2) = setup_switch(sys.argv[2]+".p4.p4info.txt",sys.argv[2]+".json","s2","127.0.0.1:50052",1,"./logs/s2_p4runtime_requests.txt")
    (s3,p4info3) = setup_switch(sys.argv[3]+".p4.p4info.txt",sys.argv[3]+".json","s3","127.0.0.1:50053",2,"./logs/s3_p4runtime_requests.txt")
    (s4,p4info4) = setup_switch(sys.argv[4]+".p4.p4info.txt",sys.argv[4]+".json","s4","127.0.0.1:50054",3,"./logs/s4_p4runtime_requests.txt")

    ShutdownAllSwitchConnections()  