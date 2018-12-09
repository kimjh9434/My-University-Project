# _*_ coding: utf-8 _*_
import socket, cv2
import time, pickle, threading

'''
pi :
 1. 구동과 동시에 ms에 접속한다.
 2. 탐지가 감지되었을때, ms로 탐지신호를 보낸다.
 ‎3. 이후 ms로부터 vm의 ip, port를 받는다.
 ‎4. 해당 주소를 이용해 vm에 접속한다.
 ‎5. vm에 동영상 정보를 보낸다.
 6. ‎다보내면 완료신호를 보내고 연결을 종료한다.
'''


class raspberryPi(object):
    def __init__(self):
        MIDHOST = "localhost"
        # MIDHOST = "192.168.48.136"
        MIDPORT = 15000
        self.midSock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        isDetected = True

        try:
            # 1. 구동과 동시에 ms에 접속한다.
            self.midSock.connect((MIDHOST, MIDPORT))  # 서버에 접속을 시도한다.
            self.midSock.send(pickle.dumps('hello midServer'))
            print(pickle.loads(self.midSock.recv(1024)))

            # 그전에 라즈베리 파이의 초음파센서와 웹캠도 켜야할 것이다.

            while True:  # 일단 계속 돌아감
                # time.sleep(1)
                print("물체를 탐지하는 중")
                print("물체를 탐지하면 1, 그외는 나머지를 입력하시오")
                inputVal = input()

                if inputVal == "1":
                    print("send")
                    isDetected = True

                    # 여기서 초음파 센서와 연동해서 물체가 탐지되는지 계속 돌아가야함.
                    # 만약 무언가 물체가 감지되었다면? isDetected 값이 True로 바뀜.
                    # 애초에 여기서는 아무것도 안하고 돌아가기만 하며, 추후, 감지되는쪽에서 바로 신호를 쏴줄수도 있음.
                    if isDetected:
                        # 2. 탐지가 감지되었을때, ms로 탐지신호를 보낸다.
                        self.midSock.send(pickle.dumps('[detected]'))

                        # 3. 이후 ms로부터 vm의 ip를 받는다.
                        vmIP = pickle.loads(self.midSock.recv(1024))

                        # ‎4. 해당 주소를 이용해 vm에 접속한다.
                        self.vmSock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                        self.vmSock.connect((vmIP, 15002))
                        self.vmSock.send(pickle.dumps('hello vmServer'))

                        udp_port = pickle.loads(self.vmSock.recv(1024))
                        print(udp_port)
                        udp_port = int(udp_port.split(",")[1])

                        self.udp_sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
                        udp_server_address = ('localhost', udp_port)
                        self.udp_sock.bind(udp_server_address)
                        print("bind success")

                        data, vmAddr = self.udp_sock.recvfrom(128)
                        print("udp client", data)

                        threading._start_new_thread(self.sendFrame, (vmAddr,))

        except  Exception as e:
            print(e)

    def sendFrame(self, udp_server_address = ()):
        print("yeah")

        capture = cv2.VideoCapture("SampleVideo_1280x720_1mb.mp4")

        while True:
            ret, frame = cv2.VideoCapture.read(capture)

            cv2.imwrite("temp2.jpg", frame)
            print("make jpg")

            f = open("temp2.jpg", "rb")

            data = f.read()
            print("send start")
            while data.__len__() != 0:
                print("send byte", data[:30000].__len__())
                self.udp_sock.sendto(data[:30000], udp_server_address)
                data = data[30000:]

            print("send over")
            self.udp_sock.sendto("EOF".encode("utf-8"), udp_server_address)
            print("send EOF")
            data, vmAddr = self.udp_sock.recvfrom(128)
            print("udp client", data)
            time.sleep(0.05)
        capture.release()

        self.vmSock.send(pickle.dumps('[quit]'))



if __name__ == "__main__":
    pi = raspberryPi()
