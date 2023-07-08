package com.weha.cashhandleapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.weha.cashhandleapp.databinding.FragmentDetailLogBinding

class DetailLogFragment : BaseFragment() {
    private val TAG = DetailLogFragment::class.java.simpleName

    private lateinit var binding: FragmentDetailLogBinding
    private var errorMessage = ""

    companion object {
        const val ERROR_MESSAGE = "ERROR_MESSAGE"
        fun newInstance(errorMessage: String): DetailLogFragment {
            return DetailLogFragment().apply {
                arguments = bundleOf().apply {
                    putString(ERROR_MESSAGE, errorMessage)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initAdapter() {

    }

    override fun initListener() {
        arguments?.let {
            it.getString(ERROR_MESSAGE)?.let { message ->
                errorMessage = message
            }
        }
        binding.tvErrorMessage.text = errorMessage
    }
}