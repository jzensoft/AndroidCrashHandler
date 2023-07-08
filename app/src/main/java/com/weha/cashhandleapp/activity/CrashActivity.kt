package com.weha.cashhandleapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.weha.cashhandleapp.MainActivity
import com.weha.cashhandleapp.adapter.ViewPageAdapter
import com.weha.cashhandleapp.databinding.ActivityCrashBinding
import com.weha.cashhandleapp.fragment.CrashFragment
import com.weha.cashhandleapp.fragment.DetailLogFragment
import com.weha.cashhandleapp.handler.GlobalExceptionHandler
import com.weha.cashhandleapp.listener.FragmentListener

class CrashActivity : AppCompatActivity(), FragmentListener {

    private val TAG = CrashActivity::class.java.simpleName

    private lateinit var binding: ActivityCrashBinding
    private lateinit var viewPageAdapter: ViewPageAdapter
    private var errorMessage = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalExceptionHandler.getThrowableFromIntent(intent).let {
            errorMessage = it?.message ?: ""
        }

        initAdapter()
        initTabLayout()
    }

    private fun initAdapter() {
        viewPageAdapter = ViewPageAdapter(supportFragmentManager, lifecycle)
        viewPageAdapter.addFragment(CrashFragment())
        viewPageAdapter.addFragment(DetailLogFragment.newInstance(errorMessage))
        binding.viewPage.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = viewPageAdapter
        }
    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPage) { tab, position ->
            run {
                tab.text = if (position == 0) "Home" else "Report"
            }
        }.attach()
    }

    override fun onResetApplication() {
        finishAffinity()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onReport() {
        binding.viewPage.post {
            binding.viewPage.setCurrentItem(1, true)
        }
    }


}