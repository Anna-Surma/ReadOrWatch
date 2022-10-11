package com.example.readorwatch

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.example.readorwatch.adapter.FragmentsAdapter
import com.example.readorwatch.api.ApiClient
import com.example.readorwatch.api.SessionManager
import com.example.readorwatch.databinding.ActivityLoginBinding
import com.example.readorwatch.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var bind: ActivityLoginBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    lateinit var sessionManager: SessionManager
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLoginBinding.inflate(layoutInflater)
        val view: View = bind.root
        setContentView(view)

        ApiClient().initialize(application)
        sessionManager = SessionManager(applicationContext)

        tabLayout = findViewById(R.id.loginTabLayout)
        viewPager = findViewById(R.id.loginViewPager)
        viewPager.adapter = FragmentsAdapter(this, tabLayout.tabCount)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.setText(R.string.signIn)
                }
                1 -> {
                    tab.setText(R.string.signUp)
                }
            }
        }.attach()

        if (Build.VERSION.SDK_INT < 29) this.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        loginViewModel.logInEvent.observe(this) {
            if (it.canLogIn && it.token != null) {
                sessionManager.saveAuthToken(it.token!!)
                startActivity(Intent(this, ChoiceActivity::class.java))
                finish()
            }
        }

        loginViewModel.loadingStatus.observe(this) {
            if (it) {
                bind.loadingIndicatorLayout?.visibility = View.VISIBLE
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                    WindowManager.LayoutParams.FLAG_DIM_BEHIND
                )
            } else {
                bind.loadingIndicatorLayout?.visibility = View.GONE
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            }
        }
    }
}