package com.example.myapplication.mvp

import android.app.Dialog
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ContextThemeWrapper

object SafeDialogHandle {

    private val mainHandler = Handler(Looper.getMainLooper())

    fun safeShowDialog(dialog: Dialog?) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            safeShowDialogOnMainThread(dialog)
            return
        }

        mainHandler.post { safeShowDialogOnMainThread(dialog) }
    }

    private fun safeShowDialogOnMainThread(dialog: Dialog?) {
        if (dialog == null || dialog.isShowing) {
            return
        }
        val bindAct = getActivity(dialog)

        if (bindAct == null || bindAct.isFinishing) {
            Log.d("Dialog shown failed:", "The Dialog bind's Activity was recycled or finished!")
            return
        }

        dialog.show()
    }

    private fun getActivity(dialog: Dialog): AppCompatActivity? {
        var bindAct: AppCompatActivity? = null
        var context = dialog.context
        do {
            if (context is AppCompatActivity) {
                bindAct = context
                break
            } else if (context is ContextThemeWrapper) {
                context = context.baseContext
            } else {
                break
            }
        } while (true)
        return bindAct
    }

    fun safeDismissDialog(dialog: Dialog?) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            safeDismissDialogOnMainThread(dialog)
            return
        }

        mainHandler.post { safeDismissDialogOnMainThread(dialog) }
    }

    private fun safeDismissDialogOnMainThread(dialog: Dialog?) {
        if (dialog == null || !dialog.isShowing) {
            return
        }

        val bindAct = getActivity(dialog)
        if (bindAct != null && !bindAct.isFinishing) {
            dialog.dismiss()
        }
    }

}