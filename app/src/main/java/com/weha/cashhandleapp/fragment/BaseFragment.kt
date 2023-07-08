package com.weha.cashhandleapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.weha.cashhandleapp.listener.FragmentListener

abstract class BaseFragment : Fragment() {
    var listener: FragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as FragmentListener
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initListener()
    }

    abstract fun initAdapter()
    abstract fun initListener()
}