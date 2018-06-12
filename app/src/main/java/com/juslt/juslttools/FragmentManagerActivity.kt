package com.juslt.juslttools

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.juslt.juslttools.fragment.FirstFragment
import com.juslt.juslttools.fragment.SecondFragment

class FragmentManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_manager)
        Log.e("===","FragmentManagerActivity----------onCreate")
//        supportFragmentManager.beginTransaction().add(R.id.fl_container, FirstFragment()).addToBackStack(null).commit()
    }

    fun showSecondFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, SecondFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun popStack() {
        supportFragmentManager.popBackStack()
    }
    override fun onStart() {
        super.onStart()
        Log.e("===","FragmentManagerActivity----------onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.e("===","FragmentManagerActivity----------onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("===","FragmentManagerActivity----------onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("===","FragmentManagerActivity----------onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("===","FragmentManagerActivity----------onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("===","FragmentManagerActivity----------onDestroy")
    }
}
