package com.example.readorwatch.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.readorwatch.databinding.ItemGridBookBinding
import com.example.readorwatch.model.Book
import com.example.readorwatch.model.OpenBookDetailsEvent

class CardViewHolder(
    private val itemGridBookBinding: ItemGridBookBinding,
    private val clickListener: OpenBookDetailsEvent
) : RecyclerView.ViewHolder(itemGridBookBinding.root)
{
    fun bindBook(book: Book)
    {
        itemGridBookBinding.bookCardCover.setImageResource(book.cover)
        itemGridBookBinding.bookCardTitle.text = book.title
        itemGridBookBinding.bookCardAuthor.text = book.author

        itemGridBookBinding.bookCardView.setOnClickListener{
            clickListener.onClick(book)
        }
    }
}