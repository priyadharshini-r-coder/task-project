package com.example.taskproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
      Toast.makeText(context,"Broadcast message is received",Toast.LENGTH_LONG).show()
    }
}