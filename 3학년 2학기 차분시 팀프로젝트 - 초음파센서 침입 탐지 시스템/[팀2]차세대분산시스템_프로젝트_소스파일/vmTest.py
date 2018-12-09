# _*_ coding: utf-8 _*_
import threading, socket
import time, pickle, cv2
from tkinter import *
from PIL import Image, ImageTk
'''
vm :
 1. 구동과 동시에 ms에 접속한다.
 2. ms로부터 탐지신호를 받으면 pi의 접속을 받는다.
 3. 이후, pi로부터 동영상정보를 받는다.
 4. 받아서 파일에 저장한다.
 5. 다 받으면 연결을 종료한다.
 6. pi와의 접속수에 따라, ms로 full, empty명령어를 보낸다.
'''


class streamingServer(object):
    def __init__(self):
        self.master = Tk()
        # pi들을 받기 위한 서버
        self.piList = {}
        self.piNum = 0
        threading._start_new_thread(self.mainFunc, ())
        self.master.mainloop()

    def mainFunc(self):
        PIHOST = ""
        PIPORT = 15002
        piSock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        piSock.bind((PIHOST, PIPORT))
        piSock.listen(1)

        threading._start_new_thread(self.listenPi, (piSock,))

        # ------------------------------------------------------------

        # HOST = "192.168.48.136"
        MIDHOST = "localhost"
        MIDPORT = 15001
        self.midSock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        try:
            # 1. 구동과 동시에 ms에 접속한다.
            self.midSock.connect((MIDHOST, MIDPORT))  # mid서버에 접속을 시도한다.
            self.midSock.send(pickle.dumps('hello midServer'))
            print(pickle.loads(self.midSock.recv(1024)))

            while True:  # 일단 계속 돌아감
                time.sleep(1)
                print("midServer로부터 메시지를 받기위해 대기중")
                msg = pickle.loads(self.midSock.recv(1024))
                print("midServer로부터 받은 메시지 : " + msg + "\n")

                # 2. ms로부터 탐지신호를 받으면 pi의 접속을 받는다.
                # if msg == '[detected]'  #굳이 이럴필요는 없는것 같다.

        except  Exception as e:
            print(e)

    def listenPi(self, piSock):
        print("bind1")
        while True:
            time.sleep(1)
            # 2. 계속 pi의 접속을 받는다.
            piConn, piAddr = piSock.accept()
            self.piNum += 1

            print("New Connected RaspberryPi : ", piAddr)
            self.piList[self.piNum] = [piConn, piAddr[0], piAddr[1]]

            threading._start_new_thread(self.piThread, (piConn, self.piNum))

            # 6. pi와의 접속수에 따라, ms로 full, empty명령어를 보낸다.
            if self.piNum == 2:  # 대충 5~10을 생각하고 있지만, 동작확인을 위해서 2~3으로 잡음
                self.midSock.send(pickle.dumps('[full]'))

    def piThread(self, piConn, piSeqNum):
        print(pickle.loads(piConn.recv(1024)))
        piConn.send(pickle.dumps('hello piTest, ' + str(15002 + piSeqNum)))

        print("sending start")
        threading._start_new_thread(self.recieveFrame, (piSeqNum, ))
        threading._start_new_thread(self.listenPi, (piConn, ))

        self.isGoing = True

        while self.isGoing:
            time.sleep(1)
            print("pi로부터 메시지를 받기위해 대기중")
            msg = pickle.loads(piConn.recv(1024))
            print("pi로부터 받은 메시지 : " + msg)
            rmsg = msg.split('/')


            if rmsg[0] == '[quit]':
                self.piNum -= 1
                # 6. pi와의 접속수에 따라, ms로 full, empty명령어를 보낸다.
                if self.piNum == 0:
                    self.midSock.send(pickle.dumps('[empty]'))
                self.isGoing = False

    def recieveFrame(self, piSeqNum):
        ##이분에서 udp 소켓 열고 리스닝 스레드 생성
        self.udp_sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        print((self.piList[piSeqNum][0], 15002 + piSeqNum))
        time.sleep(0.05)
        self.udp_sock.sendto("hi".encode("utf-8"), (self.piList[piSeqNum][1], 15002 + piSeqNum))

        print("real sending start")
        # Create a label to display the movie
        self.label = Label(self.master, height=19)
        self.label.grid(row=0, column=0, columnspan=4, sticky=W + E + N + S, padx=5, pady=5)

        fourcc = cv2.VideoWriter_fourcc(*'XVID')
        CACHE_FILE = str(self.piList[piSeqNum][1]) + "_" + str(15002 + piSeqNum) + "_" + str(time.time())
        out = cv2.VideoWriter("./record/" + CACHE_FILE + '.avi', fourcc, 10.0, (640, 480))

        data = bytearray()
        i = 1
        while self.isGoing:
            packet = self.udp_sock.recv(30000)

            if packet.find("EOF".encode("utf-8")) >= 0:
                data += packet[:packet.find("EOF".encode("utf-8"))]
                f = open("temp.jpg", "wb")
                f.write(data)
                f.close()
                i += 1
                data = packet[packet.find("EOF".encode("utf-8")) + 3:]
                print("get one frame")
                self.udp_sock.sendto("ack".encode("utf-8"), (self.piList[piSeqNum][1], 15002 + piSeqNum))
                time.sleep(0.05)
            else:
                print("plus")
                data += packet

                try:
                    photo = ImageTk.PhotoImage(Image.open("temp.jpg"))
                    self.label.configure(image=photo, height=288)
                    self.label.image = photo
                except OSError as e:
                    print(e)
                    continue

                out.write(cv2.imread("temp.jpg"))

        cv2.destroyAllWindows()

if __name__ == "__main__":
    vm = streamingServer()
