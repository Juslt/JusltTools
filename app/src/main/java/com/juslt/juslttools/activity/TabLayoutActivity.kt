package com.juslt.juslttools.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.juslt.juslttools.R
import kotlinx.android.synthetic.main.activity_tab_layout.*

class TabLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)


        /***
         *
         * tablayout  tab包含文字和图表， 以背景颜色区分tab的选中与非选中状态
         *
         */
        for (i in 0..4) {
            val tab = tab_layout.newTab()
            val view = LayoutInflater.from(this).inflate(R.layout.item_tab, null)
            val tv = view.findViewById<TextView>(R.id.tv_tab)
            val drawableTop = resources.getDrawable(R.mipmap.ic_pay_tool_tag)
            tv.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null)
            tv.compoundDrawablePadding = 4
            tv.text = "产品类型"
            tab.customView = view
            tab_layout.addTab(tab)
        }


        /***
         *
         *     tablayout_02  tab包含文字和图表，以图片高亮去区分不同选中与非选中状态
         */

        val bgs = arrayListOf<Int>(R.drawable.selector_lakala01,
                R.drawable.selector_lakala02,
                R.drawable.selector_lakala_04,
                R.drawable.selector_flash_baby,
                R.drawable.selector_credit_card)

        for (i in 0..10) {
            val tab = tab_layout_02.newTab()
            val view = LayoutInflater.from(this).inflate(R.layout.item_tab, null)
            val tv = view.findViewById<TextView>(R.id.tv_tab)
            val drawableTop = resources.getDrawable(bgs[i%4])
            tv.setCompoundDrawablesWithIntrinsicBounds(null,drawableTop,null,null)
            tv.compoundDrawablePadding =4
            tv.text="产品名称"
            tab.customView = view
            if (i == 0) {
                tv.setFocusable(true)
                tv.setTextColor(resources.getColor(R.color.blue))
            }
            tab_layout_02.addTab(tab)
        }

        tab_layout_02.setOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab!!.customView!!.findViewById<TextView>(R.id.tv_tab).setFocusable(false)
                tab.customView!!.findViewById<TextView>(R.id.tv_tab).setTextColor(resources.getColor(R.color.color_white))
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab!!.customView!!.findViewById<TextView>(R.id.tv_tab).setFocusable(true)
                tab.customView!!.findViewById<TextView>(R.id.tv_tab).setTextColor(resources.getColor(R.color.blue))
            }

        })


        /***
         *   ScrollView
         */
        for (i in 0..20) {
            val imageView = ImageView(this)
            imageView.setImageResource(bgs[i % 5])
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(20, 0, 20, 0)
            ll_container.addView(imageView, params)
        }
    }
}
