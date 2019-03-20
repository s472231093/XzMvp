package com.example.myapplication

import android.app.Application
import android.content.Context
import com.example.myapplication.net.Xznet

class MyApplication : Application() {

    companion object {
        var context:Application? = null
        fun getContext():Context{
            return context!!
        }
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext as Application?
        Xznet.instance.initNet("http://gank.io/api/")
    }


}