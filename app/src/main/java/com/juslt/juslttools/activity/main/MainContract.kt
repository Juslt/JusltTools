package com.juslt.juslttools.activity.main

import com.juslt.juslttools.mvp.BaseMvpPresenter
import com.juslt.juslttools.mvp.BaseView

/**
 * Created by juslt on 2018/6/12.
 */
object MainContract{
    interface View :BaseView{
        fun updateView()
    }
    interface Presenter:BaseMvpPresenter<View>{
        fun loadData()
    }
}