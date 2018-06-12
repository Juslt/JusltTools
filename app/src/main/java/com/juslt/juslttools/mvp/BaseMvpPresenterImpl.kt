package com.juslt.juslttools.mvp

/**
 * Created by juslt on 2018/6/12.
 */
abstract class BaseMvpPresenterImpl<V:BaseView>: BaseMvpPresenter<V> {

    var mView: V? = null

    override fun attachView(view: V) {
        mView =view
    }

    override fun detachView() {
        mView = null
    }

}