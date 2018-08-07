package com.juslt.juslttools

import android.app.Application

/**
 * Created by juslt on 2018/8/2.
 */
class App:Application(){
    companion object {
        lateinit var i: App
    }

    override fun onCreate() {
        super.onCreate()
        i=this
    }
}