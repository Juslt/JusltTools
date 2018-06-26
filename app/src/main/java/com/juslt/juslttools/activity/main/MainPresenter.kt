package com.juslt.juslttools.activity.main

import android.util.Log
import com.juslt.juslttools.activity.main.MainContract
import com.juslt.juslttools.mvp.BaseMvpPresenterImpl

/**
 * Created by juslt on 2018/6/12.
 */
class MainPresenter : BaseMvpPresenterImpl<MainContract.View>(), MainContract.Presenter {
    override fun loadData() {
        Log.e("=====","加载数据")
        mView!!.updateView()
    }

}