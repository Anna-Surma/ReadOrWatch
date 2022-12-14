package com.example.readorwatch.model

data class SignInResponse(
    val username: String,
    val email: String,
    val id: String,
    val createdAt: String,
    val avatar: Int,
    var token: String,
)