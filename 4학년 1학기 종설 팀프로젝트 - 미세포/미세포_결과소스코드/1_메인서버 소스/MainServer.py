#_*_ coding: utf-8 _*_
#MainServer.py
import threading, socket
import os
import time, pickle
import pymysql
from urllib import parse
from bs4 import BeautifulSoup
import requests
#import firebase_admin
#from firebase_admin import credentials, messaging

'''
MainServer (=ms) :
 1. pi로부터 각각 접속은 받아놓는다.
 2. pi로부터 1분마다 데이터 값을 받는다.
 3. 받은 데이터를 DB에 저장한다. 
 4. 이때, 동시에 외부 미세먼지 값도 받는다.
 5. 받은 데이터를 DB에 저장한다.
 6. 이 두 값을 휴대폰으로 전송한다.
 
 CF. 휴대폰에서 요청이 올 경우,
   가장 최근 데이터를 보내준다.
'''

#cred = credentials.Certificate('service-account.json')
#default_app = firebase_admin.initialize_app(cred)

# This registration token comes from the client FCM SDKs.
#registration_token = 'eDzcDN_tMoo:APA91bFiFn5iTqVSjdzrzbpkOZEJIEx092wbex4uDhLWv8FxI6Uf-Sxq_WzQxapVg9VfQZGf67hbfoEWxoNKd47MHny1wnStpvtmwrSFQHDtvTklaG8o6q6s3nebIRZxuNZpyO_USkIA'



class MainServer(object):
    def __init__(self):
        self.piList = {}
        self.piNum = 0

        PIHOST = ""
        PIPORT = 15000
        piSock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        piSock.bind((PIHOST, PIPORT))
        piSock.listen(1)

        threading._start_new_thread(self.listenPi, (piSock,))

        #DB 및 Table이 없으면 만든다.
        self.Create_DBandTable()

        while True:
            # 그냥 돌아가는중
            continue


    def listenPi(self, piSock):
        print("PI Bind")
        while True:
            time.sleep(1)
            # 2. 각각의 pi로부터 접속은 받아놓는다.
            piConn, piAddr = piSock.accept()
            self.piNum += 1

            print("{0}번째 New Connected RaspberryPi : ".format(self.piNum), piAddr)
            self.piList[self.piNum] = [piConn, piAddr[0], piAddr[1]]

            threading._start_new_thread(self.piThread, (piConn,))


    def piThread(self, piConn):
        print(pickle.loads(piConn.recv(1024)))   #파이로부터 메시지를 받고
        piConn.send(pickle.dumps('hello piTest'))#파이로 메시지를 보낸다.

        while True :  # 일단 계속 돌아감
            print("pi로부터 메시지를 받기위해 대기중")
            msg = pickle.loads(piConn.recv(1024))
            print("pi로부터 받은 메시지 : " + msg)
            rmsg = msg.split('/')
            in_value = int(rmsg[1])  #내부 미세먼지 측정값

            #외부 미세먼지 데이터를 받아오는 코드
            out_value = self.getOutValue()  #외부 미세먼지 측정값
            #out_value = 60

            #미세먼지 데이터 DB에 삽입
            self.Insert_Data(in_value, out_value)
            time.sleep(1)

            # 이 in_value, out_value 를 안드로이드로 보낸다.
            if in_value > 100 :
                # See documentation on defining a message payload.
                message = messaging.Message(
                    android=messaging.AndroidConfig(
                            notification=messaging.AndroidNotification(
                                body='Misefor OUTOOR ALARM WARNING : {0}'.format(in_value),
                            ),
                        ),
                    token=registration_token,
                )

                # Send a message to the device corresponding to the provided
                # registration token.
                response = messaging.send(message)
                # Response is a message ID string.
                print('Successfully sent message:', response)
    
    
            if out_value > 100 :
        
                # See documentation on defining a message payload.
                message = messaging.Message(
                    android=messaging.AndroidConfig(
                            notification=messaging.AndroidNotification(
                                body='Misefor OUTOOR ALARM WARNING : {0}'.format(out_value),
                            ),
                        ),
                    token=registration_token,
                )

                # Send a message to the device corresponding to the provided
                # registration token.
                response = messaging.send(message)
                # Response is a message ID string.
                print('Successfully sent message:', response)
            

            
    def getOutValue(self):
        URL = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst" #URL
        URL += "?" + parse.quote("ServiceKey","UTF-8") + "=nJm%2BemLmoGgCyAuOiYPiz2prCySVVuiqWmCssX0NLHtVRuA3oi9jI54aSfeNZ1jt8MEjU62UW3tPyKPHwXcmOA%3D%3D" #Service Key
        URL += "&" + parse.quote("numOfRows","UTF-8") + "=" + parse.quote("10", "UTF-8") #한 페이지 결과 수
        URL += "&" + parse.quote("pageNo","UTF-8") + "=" + parse.quote("1", "UTF-8") #페이지 번호
        URL += "&" + parse.quote("sidoName","UTF-8") + "=" + parse.quote("서울", "UTF-8") #시도 이름 (서울, 부산, 대구, 인천, 광주, 대전, 울산, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 세종)
        URL += "&" + parse.quote("searchCondition","UTF-8") + "=" + parse.quote("DAILY", "UTF-8") #요청 데이터기간 (시간 : HOUR, 하루 : DAILY)*/

        print(URL)

        response = requests.get(URL)
        html_doc = response.text
        soup = BeautifulSoup(html_doc, "lxml")

        items = soup.findAll('item')

        data = []

        for item in items:
            if '광진구' in str(item):
                print("================================================")
                print(item)
                data = item.getText()
                print("================================================")

        outdata = data.split('\n')
        del outdata[9]
        del outdata[0]
        print (outdata)
        print ("이중, 필요로 하고 있는 데이터는 미세먼지[PM10] 데이터 : {} ㎍/㎥ 와, 초미세먼지[PM2.5] 데이터 : {} ㎍/㎥ 입니다.".format(outdata[6], outdata[7]))
        return outdata[7]
            

    def Create_DBandTable(self):
        # connection 객체 생성
        connectionInstance = pymysql.connect(host="117.16.137.229", user="root", password="!@konkuk12", charset="utf8mb4",cursorclass=pymysql.cursors.DictCursor)

        try:
            # cursor 객체 생성
            cursorInsatnce = connectionInstance.cursor()

            # testDB 가 있는지 확인한다. [여기서, testDB는 만들어지고, fine_dust table도 생성된 이후에, 별도로 fine_dust table만 지운경우는 고려하지 않겠다]
            sqlQuery = "SHOW DATABASES LIKE 'testDB'"
            cursorInsatnce.execute(sqlQuery)

            ret = cursorInsatnce.fetchone()
            if ret is None : 
                print("아직 testDB가 안만들어졌음\n")
                
                # 1. testDB 생성
                i =  0
                print("testDB 생성\n")
                sqlQuery = "CREATE DATABASE testDB"
                cursorInsatnce.execute(sqlQuery)

                # CF. 전체 DATABASES 목록 확인
                sqlQuery = "SHOW DATABASES"
                cursorInsatnce.execute(sqlQuery)

                print("전체 DATABASES 목록 확인")
                databaseList = cursorInsatnce.fetchall()
                for datatbase in databaseList:
                    print(datatbase)

                # 2. 데이터베이스 목록 중 생성한 testDB 선택
                sqlQuery = "USE testDB"
                cursorInsatnce.execute(sqlQuery)

                # 3. testDB에서 fine_dust table 생성
                print("\ntestDB에서 fine_dust table 생성\n")
                sqlQuery = "CREATE TABLE fine_dust(_id INT PRIMARY KEY AUTO_INCREMENT, " \
                                                    + "in_value INT  NOT NULL, " \
                                                    + "out_value INT  NOT NULL, " \
                                                    + "register_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP" \
                                                    + ") ENGINE=INNODB DEFAULT CHARSET=utf8;"
                cursorInsatnce.execute(sqlQuery)

                # CF. 생성한 fine_dust table 확인 
                sqlQuery = "DESCRIBE fine_dust"
                cursorInsatnce.execute(sqlQuery)

                print("생성한 fine_dust table 확인")
                tableAttributeList = cursorInsatnce.fetchall()
                for tableAttribute in tableAttributeList:
                    print(tableAttribute)
            else : 
                print("이미 testDB가 만들어져 있음")

        except Exception as e:
            print("Exeception occured:{}".format(e))
         
        finally:
            connectionInstance.close()

    def Insert_Data(self, in_value, out_value):
        # connection 객체 생성
        connectionObject = pymysql.connect(host="117.16.137.229", user="root", db="testDB",password="!@konkuk12", charset="utf8mb4",cursorclass=pymysql.cursors.DictCursor)

        try:
            # cursor 객체 생성
            cursorObject = connectionObject.cursor() 

            # testDB의 fine_dust table에 in_value, out_value 값을 넣는다.
            print("DB에 데이터 값. in_value : {0}, out_value : {1} 삽입\n".format(in_value, out_value))
            sqlQuery = "INSERT INTO fine_dust (in_value, out_value) VALUES({0}, {1})".format(in_value, out_value)   
            cursorObject.execute(sqlQuery)

            connectionObject.commit()

        except Exception as e:
            print("Exeception occured:{}".format(e))
         
        finally:
            connectionObject.commit()
            connectionObject.close()

    def GetRecentData(self):
        # connection 객체 생성
        connectionObject = pymysql.connect(host="117.16.137.229", user="root", db="testDB",password="!@konkuk12", charset="utf8mb4",cursorclass=pymysql.cursors.DictCursor)

        try:
            # cursor 객체 생성
            cursorObject = connectionObject.cursor() 

            # testDB의 fine_dust table서 가장 최근 값을 읽는다.
            sqlQuery = "SELECT * FROM fine_dust ORDER BY _id DESC limit 1"   
            cursorObject.execute(sqlQuery)

            read_recent_data = cursorObject.fetchone()
            in_value = read_recent_data['in_value']
            out_value = read_recent_data['out_value']

            #CF. 읽은 값 확인
            print(read_recent_data)
            print("fine_dust table의 가장 최근 값[in_value : {0}, out_value : {1}]".format(in_value, out_value))

        except Exception as e:
            print("Exeception occured:{}".format(e))
         
        finally:
            connectionObject.close()

        return (in_value, out_value)
    

if __name__ == "__main__":
    
    server = MainServer()

