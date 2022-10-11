package com.example.readorwatch

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.example.readorwatch.databinding.ActivityChoiceBinding

class ChoiceActivity : AppCompatActivity() {
    private lateinit var bind: ActivityChoiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityChoiceBinding.inflate(layoutInflater)
        val view: View = bind.root
        setContentView(view)

        bind.bookImage.setOnClickListener(listener)
        bind.movieImage.setOnClickListener(listener)

        if (Build.VERSION.SDK_INT < 29) this.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private val listener = View.OnClickListener { view ->
        when (view.id) {
            R.id.bookImage -> {
                startActivity(Intent(this, BookHomeActivity::class.java))
            }
            R.id.movieImage-> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}