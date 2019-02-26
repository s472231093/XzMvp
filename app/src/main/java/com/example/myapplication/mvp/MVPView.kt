package com.example.myapplication.mvp

import android.app.Activity

interface MVPView {
    fun getHostActivity(): Activity
    fun showLoadingDialog()
    fun hideLoadingDialog()
    fun toastMessage(message:String)
    fun toastMessage(resId:Int)
}