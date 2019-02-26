package com.example.myapplication.mvp

import android.app.Dialog
import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import com.example.myapplication.utils.ToastUtils

class MVPViewImpl(private val activity:AppCompatActivity):MVPView {
    // 加载中的提示Dialog
    @Suppress("DEPRECATION")
    private val progressDialog: Dialog by lazy {
        val dialog = ProgressDialog(activity)
        dialog.setMessage("加载中...")
        return@lazy dialog
    }
    private val toast = ToastUtils.DEFAULT

    // 基础实现。
    override fun getHostActivity() = activity
    override fun showLoadingDialog() = SafeDialogHandle.safeShowDialog(progressDialog)
    override fun hideLoadingDialog() = SafeDialogHandle.safeDismissDialog(progressDialog)
    override fun toastMessage(message: String) = toast.show(message)
    override fun toastMessage(resId: Int) = toast.show(resId)
}