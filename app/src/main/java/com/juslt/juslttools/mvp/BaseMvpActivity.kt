package com.juslt.juslttools.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by juslt on 2018/6/12.
 */
 abstract class BaseMvpActivity<in V:BaseView,T:BaseMvpPresenter<V>>: AppCompatActivity(),BaseView {

    abstract var mPresenter:T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
    }
    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}