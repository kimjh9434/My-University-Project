package com.example.mohammed_2284.misefor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mohammed_2284.misefor.retrofit.APIKindaStuff
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.json.JSONObject



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val in_text: TextView = findViewById(R.id.in_value)
        val out_text: TextView = findViewById(R.id.out_value)
        val in_image: ImageView = findViewById(R.id.in_image)
        val out_image: ImageView = findViewById(R.id.out_image)

        val jsonObj = JsonObject()
        jsonObj.addProperty("in", "")
        jsonObj.addProperty("out", "")

        val refreshedToken = FirebaseInstanceId.getInstance().token
        if(refreshedToken.isNullOrBlank()) {

        }else{
            Log.d("FCM", "Refreshed token: " + refreshedToken!!)
        }

        btnPOST.setOnClickListener {


            APIKindaStuff
                    .service
                    .getVectors(jsonObj)
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            println("---TTTT :: POST Throwable EXCEPTION:: " + t.message)
                        }

                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                val msg = response.body()?.string()
                                println("---TTTT :: POST msg from server :: " + msg)
                                val obj = JSONObject(msg)
                                val in_string:String = obj.getString("in")
                                val out_string:String = obj.getString("out")
                                val in_int = Integer.parseInt(in_string)
                                val out_int = Integer.parseInt(out_string)
                                in_text.text = in_string
                                out_text.text = out_string

                                when (in_int){
                                    in 0..30 -> {
                                        print("좋음")
                                        in_image.setImageResource(R.mipmap.very_good)
                                    }
                                    in 31..80 -> {
                                        println("보통")
                                        in_image.setImageResource(R.mipmap.good)
                                    }
                                    in 81..120 -> {
                                        println("약간 나쁨")
                                        in_image.setImageResource(R.mipmap.bad)
                                    }
                                    in 121..200 -> {
                                        println("나쁨")
                                        in_image.setImageResource(R.mipmap.worse)
                                    }
                                    in 201..300 -> {
                                        println("매우나쁨")
                                        in_image.setImageResource(R.mipmap.worst)
                                    }
                                }
                                when (out_int){
                                    in 0..30 -> {
                                        print("좋음")
                                        out_image.setImageResource(R.mipmap.very_good)
                                    }
                                    in 31..80 -> {
                                        println("보통")
                                        out_image.setImageResource(R.mipmap.good)
                                    }
                                    in 81..120 -> {
                                        println("약간 나쁨")
                                        out_image.setImageResource(R.mipmap.bad)
                                    }
                                    in 121..200 -> {
                                        println("나쁨")
                                        out_image.setImageResource(R.mipmap.worse)
                                    }
                                    in 201..300 -> {
                                        println("매우나쁨")
                                        out_image.setImageResource(R.mipmap.worst)
                                    }
                                }
                            }
                        }
                    })
        }

        APIKindaStuff
                .service
                .getVectors(jsonObj)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        println("---TTTT :: POST Throwable EXCEPTION:: " + t.message)
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            val msg = response.body()?.string()
                            println("---TTTT :: POST msg from server :: " + msg)

                            val obj = JSONObject(msg)
                            val in_string:String = obj.getString("in")
                            val out_string:String = obj.getString("out")
                            val in_int = Integer.parseInt(in_string)
                            val out_int = Integer.parseInt(out_string)
                            in_text.text = in_string
                            out_text.text = out_string

                            when (in_int){
                                in 0..30 -> {
                                    print("좋음")
                                    in_image.setImageResource(R.mipmap.very_good)
                                }
                                in 31..80 -> {
                                    println("보통")
                                    in_image.setImageResource(R.mipmap.good)
                                }
                                in 81..120 -> {
                                    println("약간 나쁨")
                                    in_image.setImageResource(R.mipmap.bad)
                                }
                                in 121..200 -> {
                                    println("나쁨")
                                    in_image.setImageResource(R.mipmap.worse)
                                }
                                in 201..300 -> {
                                    println("매우나쁨")
                                    in_image.setImageResource(R.mipmap.worst)
                                }
                            }
                            when (out_int){
                                in 0..30 -> {
                                    print("좋음")
                                    out_image.setImageResource(R.mipmap.very_good)
                                }
                                in 31..80 -> {
                                    println("보통")
                                    out_image.setImageResource(R.mipmap.good)
                                }
                                in 81..120 -> {
                                    println("약간 나쁨")
                                    out_image.setImageResource(R.mipmap.bad)
                                }
                                in 121..200 -> {
                                    println("나쁨")
                                    out_image.setImageResource(R.mipmap.worse)
                                }
                                in 201..300 -> {
                                    println("매우나쁨")
                                    out_image.setImageResource(R.mipmap.worst)
                                }
                            }


                        }

                    }
                })



    }

}