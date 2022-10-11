package com.example.readorwatch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.readorwatch.databinding.ActivityDetailBookBinding
import com.example.readorwatch.model.BOOK_ID_EXTRA
import com.example.readorwatch.model.Book
import com.example.readorwatch.model.book_list

class DetailBookActivity : AppCompatActivity() {
    private lateinit var bind: ActivityDetailBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_detail_book)

        val bookID = intent.getIntExtra(BOOK_ID_EXTRA, -1)
        val book = bookFromID(bookID)
        if(book != null){
            bind.bookCover.setImageResource(book.cover)
            bind.bookTitle.text = book.title
            bind.bookDescription.text = book.descryption
            bind.bookAuthor.text = book.author
        }

        bind.pdfButton.setOnClickListener(listener)
    }

    private fun bookFromID(bookID: Int): Book? {
        for(book in book_list) {
            if(book.id == bookID)
                return book
        }
        return null
    }

    private val listener = View.OnClickListener { view ->
        when (view.id) {
            R.id.pdfButton -> {
                startActivity(Intent(this, PdfActivity::class.java))
            }
        }
    }
}