package com.juslt.juslttools.activity.main

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.juslt.common.utils.SysStatusBarUtil
import com.juslt.juslttools.R

/**
 * Created by wx on 2018/7/18.
 */
open class BaseActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        SysStatusBarUtil.setTransparentBar(this, R.color.colorAccent,255)
    }
}