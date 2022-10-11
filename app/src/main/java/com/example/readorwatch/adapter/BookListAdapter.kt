package com.example.readorwatch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.readorwatch.databinding.ItemGridBookBinding
import com.example.readorwatch.model.Book
import com.example.readorwatch.model.OpenBookDetailsEvent

class BookListAdapter(private val books: List<Book>,
                      private val clickListener: OpenBookDetailsEvent
)
    : RecyclerView.Adapter<CardViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder
    {
        val from = LayoutInflater.from(parent.context)
        val binding = ItemGridBookBinding.inflate(from, parent, false)
        return CardViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindBook(books[position])
    }

    override fun getItemCount(): Int = books.size

}