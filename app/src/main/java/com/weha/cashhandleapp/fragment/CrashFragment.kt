package com.weha.cashhandleapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.weha.cashhandleapp.databinding.FragmentCrashBinding

class CrashFragment : BaseFragment() {
    private lateinit var binding: FragmentCrashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCrashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initAdapter() {

    }

    override fun initListener() {
        binding.tvResetApp.setOnClickListener {
            listener?.onResetApplication()
        }
        binding.btnReport.setOnClickListener {
            listener?.onReport()
        }
    }

}