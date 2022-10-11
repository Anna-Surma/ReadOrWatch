package com.example.readorwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.readorwatch.databinding.ActivityPdfBinding

class PdfActivity : AppCompatActivity() {

    private lateinit var bind: ActivityPdfBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityPdfBinding.inflate(layoutInflater)
        val view: View = bind.root
        setContentView(view)

        bind.pdfView.fromAsset("HP.pdf").load()
    }
}