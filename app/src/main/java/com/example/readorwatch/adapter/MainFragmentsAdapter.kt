package com.example.readorwatch.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.readorwatch.fragment.GenresFragment
import com.example.readorwatch.fragment.HomeFragment

internal class MainFragmentsAdapter(fa: FragmentActivity, var totalTabs: Int) :
    FragmentStateAdapter(fa) {

    enum class ActiveTab {
        HOME, GENRES
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            ActiveTab.HOME.ordinal -> {
                HomeFragment()
            }
            ActiveTab.GENRES.ordinal -> {
                GenresFragment()
            }
            else -> createFragment(position)
        }
    }

    override fun getItemCount(): Int {
        return totalTabs
    }
}