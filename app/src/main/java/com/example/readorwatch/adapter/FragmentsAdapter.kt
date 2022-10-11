package com.example.readorwatch.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.readorwatch.fragment.SignInFragment
import com.example.readorwatch.fragment.SignUpFragment

internal class FragmentsAdapter(fa: FragmentActivity, var totalTabs: Int) :
    FragmentStateAdapter(fa) {

    enum class ActiveTab {
        SIGN_IN, SIGN_UP
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            ActiveTab.SIGN_IN.ordinal -> {
                SignInFragment()
            }
            ActiveTab.SIGN_UP.ordinal -> {
                SignUpFragment()
            }
            else -> createFragment(position)
        }
    }

    override fun getItemCount(): Int {
        return totalTabs
    }
}