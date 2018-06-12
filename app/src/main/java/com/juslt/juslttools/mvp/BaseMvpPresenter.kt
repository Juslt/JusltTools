package com.juslt.juslttools.mvp

/**
 * Created by juslt on 2018/6/12.
 */
interface BaseMvpPresenter<in V:BaseView>{
    fun attachView(view:V)
    fun detachView()
}