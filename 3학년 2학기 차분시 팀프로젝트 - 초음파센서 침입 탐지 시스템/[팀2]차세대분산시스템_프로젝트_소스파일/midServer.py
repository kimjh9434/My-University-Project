#_*_ coding: utf-8 _*_
import threading, socket
import os
import time, pickle
'''
middleServer (=ms) :
 1. 구동과 동시에 도커 컨테이너 이미지(vm) 1개를 실행시킨다.
 2. pi와, vm으로부터 각각 접속은 받아놓는다.
 3. pi로부터 탐지신호를 받으면, 남는 활성화된 vm의 ip, port를 pi로 보내고, vm으로도 탐지되었다고 알린다.
 4. vm으로부터 full, empty명령어를 받으면 적절하게 처리한다.
'''


class midServer(object):
    def __init__(self):
        self.piList = {}
        self.vmList = {}
        self.piNum = 0
        self.vmNum = 0

        self.vmConn = 0
        self.vmAddr = 0
        self.vmCount= 0
        self.vmStartNum = 0
        self.vmEndNum = 0

        PIHOST = ""
        PIPORT = 15000
        piSock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        piSock.bind((PIHOST, PIPORT))
        piSock.listen(1)

        VMHOST = ""
        VMPORT = 15001
        vmSock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        vmSock.bind((VMHOST, VMPORT))
        vmSock.listen(1)

        threading._start_new_thread(self.listenPi, (piSock,))
        threading._start_new_thread(self.listenVm, (vmSock,))

        # 1. 구동과 동시에 도커 컨테이너 이미지(vm) 1개를 실행시킨다.
        #os.system ( "echo 'dpfdptm94!' | sudo docker run -d --name vmtest{0} -v /home/sangwon/PycharmProjects/softwareArchitectingMethodology/record:/data vmtest:0.4".format(self.vmStartNum) )
        #os.system ( "echo ' ' | sudo docker restart vmtest{0}".format(self.vmStartNum) )
        self.vmStartNum+=1
        self.vmCount += 1

        while True:
            # 그냥 돌아가는중
            continue


    def listenPi(self, piSock):
        print("bind1")
        while True:
            time.sleep(1)
            # 2. pi와, vm으로부터 각각 접속은 받아놓는다. - pi
            piConn, piAddr = piSock.accept()
            self.piNum += 1

            print("New Connected RaspberryPi : ", piAddr)
            self.piList[self.piNum] = [piConn, piAddr[0], piAddr[1]]

            threading._start_new_thread(self.piThread, (piConn,))

    def piThread(self, piConn):
        print(pickle.loads(piConn.recv(1024)))
        piConn.send(pickle.dumps('hello piTest'))

        isGoing = True
        while isGoing :
          #  time.sleep(1)
            print("pi로부터 메시지를 받기위해 대기중")
            msg = pickle.loads(piConn.recv(1024))
            print("pi로부터 받은 메시지 : " + msg+"\n")

            # 3. pi로부터 탐지신호를 받으면, 남는 활성화된 vm의 ip를 pi로 보내고, vm으로도 탐지되었다고 알린다.
            if msg == '[detected]' :
                piConn.send(pickle.dumps(self.vmAddr[0]))
                #self.vmConn.send('[detected]')
			

    def listenVm(self, vmSock):
        print("bind2")
        while True:
            time.sleep(1)
            # 2. pi와, vm으로부터 각각 접속은 받아놓는다. - vm
            vmConn, vmAddr = vmSock.accept()
            self.vmNum += 1
            self.vmConn = vmConn
            self.vmAddr = vmAddr

            print("New Connected VirtualMachine : ", vmConn)
            self.vmList[self.vmNum] = [vmConn, vmAddr[0], vmAddr[1]]
            print("VirtualMachine List : ", self.vmList)

            threading._start_new_thread(self.vmThread, (vmConn,))

    def vmThread(self, vmConn):
        print(pickle.loads(vmConn.recv(1024)))
        vmConn.send(pickle.dumps('hello vmTest'))

        isGoing = True
        while isGoing :
            time.sleep(1)
            print("vm로부터 메시지를 받기위해 대기중")
            msg = pickle.loads(vmConn.recv(1024))
            print("vm로부터 받은 메시지 : " + msg+"\n")

            #  4. vm으로부터 full, empty명령어를 받으면 적절하게 처리한다.
            if msg == '[full]' : #[full] : 해당 vm과 연결된 pi가 꽉참. 대충 5개
                # 새로운 vm을 만들고, 이후 그쪽으로 연결시킨다.
                os.system ( "echo ' ' | sudo docker run -d --name vmtest{0} -v /home/sangwon/PycharmProjects/softwareArchitectingMethodology/record:/data vmtest:0.4".format(self.vmStartNum) )
                #os.system ( "echo ' ' | sudo docker restart vmtest:{0}".format(self.vmStartNum) )
                self.vmStartNum+=1
                self.vmCount   +=1
            elif msg == '[empty]' : #[empty] : 해당 vm과 연결된 pi가 없음. 
                # 해당 vm을 없앤다. (최소 1개의 vm만 유지)
                if self.vmCount > 1 :  
                    os.system ( "echo ' ' | sudo docker stop vmtest{0}".format(self.vmEndNum) )
                    os.system ( "echo ' ' | sudo docker rm vmtest{0}".format(self.vmEndNum) )
                    self.vmEndNum+=1
                    self.vmCount -=1

if __name__ == "__main__":
    server = midServer()
