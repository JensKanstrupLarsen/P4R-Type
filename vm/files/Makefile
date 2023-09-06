BMV2_SWITCH_EXE = simple_switch_grpc

test: | clean build network

test_nat: | clean build_nat network_nat

test_lb: | clean build_lb network_lb

build:
	mkdir build
	mkdir logs
	p4c-bm2-ss --p4v 16 --p4runtime-files build/config1.p4.p4info.txt,build/config1.p4.p4info.json -o build/config1.json config1.p4
	p4c-bm2-ss --p4v 16 --p4runtime-files build/config2.p4.p4info.txt,build/config2.p4.p4info.json -o build/config2.json config2.p4

build_nat:
	mkdir build
	mkdir logs
	p4c-bm2-ss --p4v 16 --p4runtime-files build/config1.p4.p4info.txt,build/config1.p4.p4info.json -o build/config1.json config1.p4
	p4c-bm2-ss --p4v 16 --p4runtime-files build/config2.p4.p4info.txt,build/config2.p4.p4info.json -o build/config2.json config2.p4
	p4c-bm2-ss --p4v 16 --p4runtime-files build/config2_nat.p4.p4info.txt,build/config2_nat.p4.p4info.json -o build/config2_nat.json config2_nat.p4

build_lb:
	mkdir build
	mkdir logs
	p4c-bm2-ss --p4v 16 --p4runtime-files build/config1.p4.p4info.txt,build/config1.p4.p4info.json -o build/config1.json config1.p4
	p4c-bm2-ss --p4v 16 --p4runtime-files build/config1_lb.p4.p4info.txt,build/config1_lb.p4.p4info.json -o build/config1_lb.json config1_lb.p4
	p4c-bm2-ss --p4v 16 --p4runtime-files build/config2.p4.p4info.txt,build/config2.p4.p4info.json -o build/config2.json config2.p4

network:
	(sleep 7s; python3 setup.py "./build/config1" "./build/config1" "./build/config2" "./build/config2"; echo "Ready to receive requests!") & sudo python3 utils/run_exercise.py -t topology.json -b simple_switch_grpc

network_nat:
	(sleep 7s; python3 setup.py "./build/config1" "./build/config1" "./build/config2" "./build/config2_nat"; echo "Ready to receive requests!") & sudo python3 utils/run_exercise.py -t topology.json -b simple_switch_grpc

network_lb:
	(sleep 7s; python3 setup.py "./build/config1_lb" "./build/config1" "./build/config2" "./build/config2"; echo "Ready to receive requests!") & sudo python3 utils/run_exercise.py -t topology.json -b simple_switch_grpc

clean:
	sudo mn -c
	sudo rm -rf build
	sudo rm -rf logs
