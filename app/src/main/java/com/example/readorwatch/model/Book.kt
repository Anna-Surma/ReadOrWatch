package com.example.readorwatch.model

var book_list = mutableListOf<Book>()

val BOOK_ID_EXTRA = "bookExtra"
data class Book (
    val cover: Int,
    val author: String,
    val title: String,
    val descryption: String,
    val id: Int? = book_list.size
)