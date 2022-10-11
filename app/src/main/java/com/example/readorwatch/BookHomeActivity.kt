package com.example.readorwatch

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.readorwatch.adapter.BookListAdapter
import com.example.readorwatch.databinding.ActivityBookHomeBinding
import com.example.readorwatch.model.BOOK_ID_EXTRA
import com.example.readorwatch.model.Book
import com.example.readorwatch.model.OpenBookDetailsEvent
import com.example.readorwatch.model.book_list
import java.io.BufferedReader
import java.io.InputStreamReader

class BookHomeActivity : AppCompatActivity(), OpenBookDetailsEvent {

    private lateinit var bind: ActivityBookHomeBinding

    private val tittlelist = mutableListOf<String>()
    private val autorlist = mutableListOf<String>()
    private val desclist = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_book_home)
        downloadBooksData()
        createBook()

        val bookHomeActivity = this

        bind.coverRecycleView.apply {
            layoutManager  = GridLayoutManager(applicationContext, 2)
            adapter = BookListAdapter(book_list, bookHomeActivity)
        }

        if (Build.VERSION.SDK_INT < 29) this.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    override fun onClick(book: Book) {
        Log.i("MainActivity", "onCreate Called")
        val intent = Intent(applicationContext, DetailBookActivity::class.java)
        intent.putExtra(BOOK_ID_EXTRA, book.id)
        startActivity(intent)
    }

    private fun downloadBooksData(){
        val miniput = InputStreamReader(assets.open("book_text.csv"))
        val reader = BufferedReader(miniput)
        var line : String?

        while(reader.readLine().also { line = it } != null){
            val row : List<String> = line!!.split("|")
            tittlelist.add(row[0])
            autorlist.add(row[1])
            desclist.add(row[2])
        }
    }

    private fun createBook(){
        val book1 = Book(
            R.drawable.harry,
            autorlist[0],
            tittlelist[0],
            desclist[0]
        )
        book_list.add(book1)

        val book2 = Book(
            R.drawable.in_the_woods,
            autorlist[1],
            tittlelist[1],
            desclist[1]
        )
        book_list.add(book2)

        val book3 = Book(
            R.drawable.six,
            autorlist[2],
            tittlelist[2],
            desclist[2]
        )
        book_list.add(book3)

        val book4 = Book(
            R.drawable.tess,
            autorlist[3],
            tittlelist[3],
            desclist[3]
        )
        book_list.add(book4)

        val book5 = Book(
            R.drawable.wilder_girls,
            autorlist[4],
            tittlelist[4],
            desclist[4]
        )
        book_list.add(book5)

        val book6 = Book(
            R.drawable.the_girl,
            autorlist[5],
            tittlelist[5],
            desclist[5]
        )
        book_list.add(book6)

        val book7 = Book(
            R.drawable.a_million_to_one,
            autorlist[6],
            tittlelist[6],
            desclist[6]
        )

        book_list.add(book7)
        book_list.add(book1)
        book_list.add(book2)
        book_list.add(book3)
        book_list.add(book4)
        book_list.add(book5)
        book_list.add(book6)
        book_list.add(book7)
    }
}