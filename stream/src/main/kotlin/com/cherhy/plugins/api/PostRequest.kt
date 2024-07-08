package com.cherhy.plugins.api

import com.cherhy.plugins.domain.PostCategory

data class GetPostRequest(
    val keyword: String?,
    val category: PostCategory?,
)

data class CreatePostRequest(
    val title: String,
    val content: String,
    val category: PostCategory,
)

data class UpdatePostRequest(
    val title: String,
    val content: String,
    val category: PostCategory,
)