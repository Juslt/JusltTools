package com.juslt.juslttools.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juslt.juslttools.FragmentManagerActivity

import com.juslt.juslttools.R
import kotlinx.android.synthetic.main.fragment_one.*
import kotlinx.android.synthetic.main.fragment_second.*


/**
 * A simple [Fragment] subclass.
 */
class SecondFragment : Fragment() {


//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//        Log.e("===","SecondFragment----------onCreateView")
//        return inflater!!.inflate(R.layout.fragment_second, container, false)
//    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.e("===","SecondFragment----------onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("===","SecondFragment----------onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("===","SecondFragment----------onActivityCreated")
        btn_back.setOnClickListener {
            (activity as FragmentManagerActivity).popStack()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("===","SecondFragment----------onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("===","SecondFragment----------onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("===","SecondFragment----------onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("===","SecondFragment----------onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("===","SecondFragment----------onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("===","SecondFragment----------onDetach")
    }



}
