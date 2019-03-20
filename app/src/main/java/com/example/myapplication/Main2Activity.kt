package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import com.example.myapplication.net.Xznet
import com.example.myapplication.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.*

class Main2Activity : AppCompatActivity(){

    private val conStrainSet = ConstraintSet()
    private val conStrainSet1 = ConstraintSet()
    private val conStrainSet2 = ConstraintSet()
    private var caseType: Int = 0
    private var conStrainlayout : ConstraintLayout? = null


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        conStrainSet.clone(this,R.layout.activity_main2)
        conStrainSet1.clone(this,R.layout.activity_main3)
        conStrainSet2.clone(this,R.layout.activity_main4)
        btn_one.setOnClickListener {
            Api()
        }

        btn_four.setOnClickListener {
            swichBtn(caseType)
        }
    }

    private fun swichBtn(case:Int){
        when(case){
            0 ->{
                conStrainlayout = findViewById(R.id.conStrain)
                TransitionManager.beginDelayedTransition(conStrainlayout)
                conStrainSet1.applyTo(conStrainlayout)
                caseType = 1
            }
            1 ->{
                conStrainlayout = findViewById(R.id.conStrain)
                TransitionManager.beginDelayedTransition(conStrainlayout)
                conStrainSet2.applyTo(conStrainlayout)
                caseType = 2
            }
            2 ->{
                conStrainlayout = findViewById(R.id.conStrain)
                TransitionManager.beginDelayedTransition(conStrainlayout)
                conStrainSet.applyTo(conStrainlayout)
                caseType = 0
            }
        }
    }

    private fun Api(){
        GlobalScope.launch {
            withContext(Dispatchers.IO){
                val request = Xznet.instance.service.getGankData("all",10,1)
                try {
                    val response = request.await()
                }catch (e:Throwable){

                }
            }
//            async {
//                val request = Xznet.instance.service.getGankData("all",10,1)
//                ToastUtils.DEFAULT.show("aaaaaaaaaaa","走到这一步")
//                try {
//                    val response = request.await()
//                    ToastUtils.DEFAULT.show("dasdasd","走到这一步")
//                }catch (e:Throwable){
//
//                }
//            }
        }
    }
}
