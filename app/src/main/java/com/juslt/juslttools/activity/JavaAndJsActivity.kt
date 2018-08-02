package com.juslt.juslttools.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.service.carrier.CarrierMessagingService
import android.support.annotation.RequiresApi
import android.webkit.*
import android.widget.Toast
import com.juslt.juslttools.R
import com.juslt.juslttools.activity.main.BaseActivity
import com.juslt.juslttools.activity.main.MainActivity
import kotlinx.android.synthetic.main.activity_java_and_js.*

class JavaAndJsActivity : BaseActivity() {




    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_java_and_js)

        web_view.loadUrl("file:///android_asset/test.html")



        /**
         * return true，则在打开新的url时WebView就不会再加载新的url了，所有处理都需要在WebView中操作，包含加载；
         * return false，则系统就认为上层没有做处理，接下来还是会继续加载新的url的；默认return false
         * */
        web_view.webViewClient = object :WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                if(url=="file:///android_asset/test2.html"){
                    startActivity(Intent(this@JavaAndJsActivity,MainActivity::class.java))
                    return true
                }else{
                    web_view.loadUrl(url)
                    return false
                }
            }
        }


        val webSetting = web_view.settings
        webSetting.javaScriptEnabled =true   //设置支持java与JS交互
        web_view.addJavascriptInterface(JsInteration(),"android")



        btn_invoke_h5.setOnClickListener {
//            web_view.loadUrl("JavaScript:show()")
//            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT){
//                getH5BackValue()
//            }
            }
        }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun getH5BackValue() {
        web_view.evaluateJavascript("sum(1,2)"
        ) { value -> Toast.makeText(this@JavaAndJsActivity,value,Toast.LENGTH_SHORT).show() }
    }

    public class JsInteration{
        @JavascriptInterface
        fun back():String{
            return "我是java里边的内容"
        }
    }

}
