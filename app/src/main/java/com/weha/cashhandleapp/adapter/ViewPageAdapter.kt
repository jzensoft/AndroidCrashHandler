package com.weha.cashhandleapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragment = mutableListOf<Fragment>()

    fun addFragment(fragment: Fragment) {
        this.fragment.add(fragment)
    }

    override fun getItemCount() = fragment.size

    override fun createFragment(position: Int) = fragment[position]
}