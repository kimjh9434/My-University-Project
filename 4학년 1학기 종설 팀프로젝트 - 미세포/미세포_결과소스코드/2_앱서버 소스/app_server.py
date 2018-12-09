from flask import Flask, request, jsonify
import pymysql
import firebase_admin
import threading
import time
from firebase_admin import credentials, messaging

app = Flask(__name__)

cred = credentials.Certificate('service-account.json')
default_app = firebase_admin.initialize_app(cred)

# This registration token comes from the client FCM SDKs.
registration_token = 'c_XXffBDW0o:APA91bFyRaN3JA288e_X0n2GMwbgC_tD8X1FoHMCyWEEgAcAdnJoho1mX-2QXI4fdEmyDgTo3r_hERKv699AU-qh5w6BwGi4hDLBVjgiioD7MyL1UMojL0StJSumh4240aFrvzFqKPvo'

# root
@app.route("/")
def index():
    """
    this is a root dir of my server
    :return: str
    """
    return "This is root!!!!"


# GET
@app.route('/users/<user>')
def hello_user(user):
    """
    this serves as a demo purpose
    :param user:
    :return: str
    """
    connectionObject = pymysql.connect(host="117.16.137.229", user="root", db="testDB",password="!@konkuk12", charset="utf8mb4",cursorclass=pymysql.cursors.DictCursor)

    try:
        cursorObject = connectionObject.cursor() 

        sqlQuery = "SELECT * FROM fine_dust ORDER BY _id DESC limit 1"   
        cursorObject.execute(sqlQuery)

        read_recent_data = cursorObject.fetchone()
        in_value = read_recent_data['in_value']
        out_value = read_recent_data['out_value']

        print(read_recent_data)
        print("fine_dust table [in_value : {0}, out_value : {1}]".format(in_value, out_value))

    except Exception as e:
        print("Exeception occured:{}".format(e))
     
    finally:
        connectionObject.close()

    return ("fine_dust table [in_value : {0}, out_value : {1}]".format(in_value, out_value))


# POST
@app.route('/api/post_some_data', methods=['POST'])
def get_text_prediction():
    connectionObject = pymysql.connect(host="117.16.137.229", user="root", db="testDB",password="!@konkuk12", charset="utf8mb4",cursorclass=pymysql.cursors.DictCursor)

    try:
        cursorObject = connectionObject.cursor() 

        sqlQuery = "SELECT * FROM fine_dust ORDER BY _id DESC limit 1"   
        cursorObject.execute(sqlQuery)

        read_recent_data = cursorObject.fetchone()
        in_value = read_recent_data['in_value']
        out_value = read_recent_data['out_value']

        print(read_recent_data)
        print("fine_dust table [in_value : {0}, out_value : {1}]".format(in_value, out_value))

    except Exception as e:
        print("Exeception occured:{}".format(e))
     
    finally:
        connectionObject.close()
    
    
    return jsonify({'in': in_value, 'out': out_value})
    

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)

