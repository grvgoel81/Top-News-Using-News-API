package com.example.novopayassignment.model

data class BaseModel<T>(
    val status: String?,
    val message: String?,
    val articles: T
)
