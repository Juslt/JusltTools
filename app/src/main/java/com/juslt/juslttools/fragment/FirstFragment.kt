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


/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("===","firstFragment----------onCreateView")
        return inflater!!.inflate(R.layout.fragment_one, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.e("===","firstFragment----------onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("===","firstFragment----------onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("===","firstFragment----------onActivityCreated")
        btn_open_secondFragment.setOnClickListener {
            (activity as FragmentManagerActivity).showSecondFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("===","firstFragment----------onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("===","firstFragment----------onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("===","firstFragment----------onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("===","firstFragment----------onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("===","firstFragment----------onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("===","firstFragment----------onDetach")
    }
}
