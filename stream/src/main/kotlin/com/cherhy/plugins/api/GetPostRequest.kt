package com.cherhy.plugins.api

// TODO: category should be enum
data class GetPostRequest(
    val title: String,
    val content: String,
    val category: String,
)