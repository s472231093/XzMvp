package com.example.myapplication.mvp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife

abstract class BaseMVPActivity: AppCompatActivity(), MVPView {

    // 一个Activity持有一个唯一的Dispatcher派发器。
    private val mvpDispatcher = MVPDispatcher()
    // 一个Activity持有一个唯一的model做基础交互展示
    val viewModel by lazy { MVPViewImpl(this) }

    // 然后在对应生命周期进行派发
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = getLayoutId()
        if (layoutId != 0) {
            setContentView(layoutId)
            ButterKnife.bind(this)
        }
        initPage(savedInstanceState)

        createPresenters()?.forEach { mvpDispatcher.addPresenter(it) }

        mvpDispatcher.dispatchOnCreate(intent?.extras)
    }

    override fun onStart() {
        super.onStart()
        mvpDispatcher.dispatchOnStart()
    }

    override fun onResume() {
        super.onResume()
        mvpDispatcher.dispatchOnResume()
    }

    override fun onPause() {
        super.onPause()
        mvpDispatcher.dispatchOnPause()
    }

    override fun onStop() {
        super.onStop()
        mvpDispatcher.dispatchOnStop()
    }

    override fun onRestart() {
        super.onRestart()
        mvpDispatcher.dispatchOnRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
        mvpDispatcher.dispatchOnDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mvpDispatcher.dispatchOnActivityResult(requestCode, resultCode, data)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mvpDispatcher.dispatchOnSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        mvpDispatcher.dispatchOnRestoreInstanceState(savedInstanceState)
    }

    // 交由统一的交互器进行统一样式展示
    override fun getHostActivity() = this
    override fun showLoadingDialog() = viewModel.showLoadingDialog()
    override fun hideLoadingDialog() = viewModel.hideLoadingDialog()
    override fun toastMessage(message: String) = viewModel.toastMessage(message)
    override fun toastMessage(resId: Int) = viewModel.toastMessage(resId)

    /**
     * 指定使用的LayoutID，用于进行setContentView操作。当return 0时，则代表不使用
     */
    abstract fun getLayoutId():Int
    abstract fun initPage(savedInstanceState: Bundle?)
    /**
     * 继承此方法，提供需要与此页面相绑定的Presenter, 可绑定多个Presenter
     */
    open fun createPresenters():Array<out MVPPresenter<*>>? = null

}