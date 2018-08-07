package com.juslt.juslttools.activity.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import com.juslt.juslttools.FragmentManagerActivity
import com.juslt.juslttools.R
import com.juslt.juslttools.activity.*
import com.juslt.juslttools.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<MainContract.View, MainPresenter>(), MainContract.View {
    override fun updateView() {
        Log.e("=====","更新view")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_fragment_manager.setOnClickListener {
            startActivity(Intent(this, FragmentManagerActivity::class.java))
        }
        tv_permission.setOnClickListener {
            startActivity(Intent(this,PermissionActivity::class.java))
        }
        tv_side_bar.setOnClickListener {
            startActivity(Intent(this,ContractSideBarActivity::class.java))
        }
        tv_matisse.setOnClickListener {
            startActivity(Intent(this, PhotoPickerActivity::class.java))
        }
        tv_java_js.setOnClickListener {
            startActivity(Intent(this, JavaAndJsActivity::class.java))
        }
        tv_tab_layout.setOnClickListener {
            startActivity(Intent(this,TabLayoutActivity::class.java))
        }
        tv_word_filter.setOnClickListener {
            startActivity(Intent(this,WordFilterActivity::class.java))
        }



        Log.e("===", "MainActivity----------onCreate")
        mPresenter.loadData()
    }

    override var mPresenter = MainPresenter()





    override fun onStart() {
        super.onStart()
        Log.e("===", "MainActivity----------onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("===", "MainActivity----------onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("===", "MainActivity----------onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("===", "MainActivity----------onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("===", "MainActivity----------onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("===", "MainActivity----------onDestroy")
    }
}
