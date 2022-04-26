package com.example.taskproject

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BroadCastReceiverApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_cast_receiver_app)
        val intent=IntentFilter("com.example.myBroadcastMessage")
        val broadCastReceiver=MyBroadCastReceiver()
        registerReceiver(broadCastReceiver,intent)
    }
}