package com.cherhy.plugins.api

import com.cherhy.common.util.model.Keyword
import com.cherhy.plugins.domain.PostCategory
import com.cherhy.plugins.domain.PostContent
import com.cherhy.plugins.domain.PostTitle

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