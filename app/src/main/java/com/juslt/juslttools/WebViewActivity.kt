package com.juslt.juslttools

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.ValueCallback
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        web_view.loadUrl("https://creditcardapp.bankcomm.com/applynew/front/apply/new/reversion/saveCardInfo.html")
        val webSetting = web_view.settings
        webSetting.javaScriptEnabled = true
        val number = "411328********"
        val js = "javascript:document.getElementById('pccc_certNo').value='"+number+"';"
        web_view.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if(Build.VERSION.SDK_INT>19){
                    view!!.evaluateJavascript(js,object :ValueCallback<String>{
                        override fun onReceiveValue(value: String?) {

                        }
                    })
                }else{
                    view!!.loadUrl(js)
                }
            }
        }
    }
}
