package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.net.Api

class Main2Activity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initView()

    }

    private fun initView(){
        var len = 1

        while (len==1){
            len = 2
            Log.e("while循环","   "+len)
            break
        }
    }
}
