package com.example.readorwatch.model

data class SignUpResponse(
    val username: String,
    val email: String,
    val avatar: Int,
    val id: String,
    val createdAt: String,
)