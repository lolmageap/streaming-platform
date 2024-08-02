package com.cherhy.api

import com.cherhy.common.util.model.Keyword
import com.cherhy.domain.PostCategory
import com.cherhy.domain.PostContent
import com.cherhy.domain.PostTitle

data class GetPostRequest(
    val keyword: Keyword?,
    val category: PostCategory?,
)

data class CreatePostRequest(
    val title: PostTitle,
    val content: PostContent,
    val category: PostCategory,
)

data class UpdatePostRequest(
    val title: PostTitle,
    val content: PostContent,
    val category: PostCategory,
)