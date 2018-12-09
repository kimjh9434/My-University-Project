# _*_ coding: utf-8 _*_
#RaspberryPi.py
import socket
import time, pickle
import random    #추후에 지워야함. 임시 패키지

import serial


ser = serial.Serial(port = '/dev/ttyACM0',
		    baudrate=115200,
)


class raspberryPi(object):
    def __init__(self):
        #MAINHOST = "localhost" #테스트용
        MAINHOST = "117.16.137.226" #실제용 -- 오픈스택 알파 인스턴스 [Server]
        MAINPORT = 15000
        self.mainSock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        print("Arduino Serial is open : " + str(ser.isOpen()))

        try:
            # 1. 구동과 동시에 ms에 접속한다.
            self.mainSock.connect((MAINHOST, MAINPORT))  # 서버에 접속을 시도한다.
            self.mainSock.send(pickle.dumps('hello mainServer')) #서버로 메시지를 보내고
            print(pickle.loads(self.mainSock.recv(1024)))        #서버에서 응답 메시지를 받는다.


            i = 0
            while True:  # 일단 계속 돌아감
                print("Sensing [60 sec]")
                #미세먼지를 측정하는 코드

                if ser.readable():
                    readData = ser.readline()
                    in_value_str = readData.decode()[:len(readData)-1]
                    print(in_value_str)
                    in_value = round(float(in_value_str))          #내부 미세먼지 측정값 - 실제 측정값
                    print(in_value)
                      
                # in_value = random.randrange(0,100)  #내부 미세먼지 측정값 - 임시 램덤값

                print("[{0}] fine dust data : {1} Sending".format(i, in_value))
                self.mainSock.send(pickle.dumps("data/{0}".format(in_value)))
                time.sleep(60) # 일반 데이터를 60초 = 1분 마다 전송
                i+=1

        except  Exception as e:
            print(e)

if __name__ == "__main__":
    pi = raspberryPi()

