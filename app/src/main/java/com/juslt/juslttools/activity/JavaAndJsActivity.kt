package com.juslt.juslttools.activity

import android.annotation.SuppressLint
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.service.carrier.CarrierMessagingService
import android.support.annotation.RequiresApi
import android.util.Log
import android.webkit.*
import android.widget.Toast
import com.juslt.juslttools.App
import com.juslt.juslttools.R
import com.juslt.juslttools.activity.main.BaseActivity
import com.juslt.juslttools.activity.main.MainActivity
import kotlinx.android.synthetic.main.activity_java_and_js.*
import java.util.*

class JavaAndJsActivity : BaseActivity(), Observer {

    override fun update(o: Observable?, arg: Any?) {
        if (o is JsNotify) {


        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        JsNotify.addObserver(this)
        setContentView(R.layout.activity_java_and_js)
//        web_view.loadUrl("file:///android_asset/test.html")
        web_view.loadUrl("http://static.kuaifuba.cn/app/blitz/shangjixiangqing.html")


        /**
         * return true，则在打开新的url时WebView就不会再加载新的url了，所有处理都需要在WebView中操作，包含加载；
         * return false，则系统就认为上层没有做处理，接下来还是会继续加载新的url的；默认return false
         * */
        web_view.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                if (url == "file:///android_asset/test2.html") {
                    startActivity(Intent(this@JavaAndJsActivity, MainActivity::class.java))
                    return true
                } else {
                    web_view.loadUrl(url)
                    return false
                }
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Log.e("===", "onPageFinished")
//                web_view.loadUrl("JavaScript:states("+ "'" + "已签约" + "'"+ ")" )
            }
        }


        val webSetting = web_view.settings
        webSetting.javaScriptEnabled = true   //设置支持java与JS交互
        web_view.addJavascriptInterface(JsInteration(object :JsInteration.CallBack{
            override fun getProductId(id: String) {
                Log.e("===","update:"+id)
                web_view.loadUrl("JavaScript:states(" + "'" + "已签约" + "'" + ")")
            }

        }), "android")


//        web_view.loadUrl("JavaScript:show()")
        btn_invoke_h5.setOnClickListener {
            web_view.loadUrl("JavaScript:javaCallJs(" + "'" + "已签约" + "'" + ")")
//            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT){
//                getH5BackValue()
//            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun getH5BackValue() {
        web_view.evaluateJavascript("sum(1,2)"
        ) { value -> Toast.makeText(this@JavaAndJsActivity, value, Toast.LENGTH_SHORT).show() }
    }

    class JsInteration(val callback:CallBack) {
        @JavascriptInterface
        fun back(id: Int): String {
//            Toast.makeText(App.i,"hahaha",Toast.LENGTH_LONG).show()
            Log.e("===", "Js back :" + id)
            return "我是java里边的内容"
        }

        @JavascriptInterface
        fun idname(id: String) {
            Log.e("===", "productId :" + id)
        }

        @JavascriptInterface
        fun states(id: String) {
            Log.e("===", "productId :" + id)
//            JsNotify.refreshH5(id)
            callback.getProductId(id)
        }

        interface CallBack{
            fun getProductId(id:String)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        JsNotify.deleteObserver(this)
    }


    object JsNotify : Observable() {
        fun refreshH5(productId: String) {
            setChanged()
            notifyObservers(productId)
        }
    }

}
