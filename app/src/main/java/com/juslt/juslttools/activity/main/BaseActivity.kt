package com.juslt.juslttools.activity.main

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.juslt.common.utils.SysStatusBarUtil
import com.juslt.juslttools.R

/**
 * Created by wx on 2018/7/18.
 */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
//        SysStatusBarUtil.setTransparentBar(this, R.color.colorAccent, 255)
    }

     fun setTmmersiveStatusBar(statusBarColor: Int, fontIconDark: Boolean = false) {


        if(fontIconDark){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                setStatusBarFontIconDark(true)
            }else{
                if(statusBarColor ==Color.WHITE){
//                    color = this.resources.getColor(R.color.gray)
                }
            }
        }else{
            setTranslucentStatus()
        }
    }

    private fun setStatusBarFontIconDark(dark: Boolean) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(dark){
                window.decorView.systemUiVisibility =View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }

    //设置状态栏透明
    private fun setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
}