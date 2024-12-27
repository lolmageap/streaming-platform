package com.cherhy.api

import com.cherhy.common.util.model.Keyword
import com.cherhy.domain.PostCategory
import com.cherhy.domain.PostContent
import com.cherhy.domain.PostTitle
import com.cherhy.usecase.CreatePostCommand
import com.cherhy.usecase.GetPostQuery
import com.cherhy.usecase.UpdatePostCommand

data class GetPostRequest(
    val keyword: String?,
    val category: String?,
) {
    fun toQuery() =
        GetPostQuery(
            keyword = keyword?.let { Keyword(it) },
            category = category?.let { PostCategory.valueOf(it) }
        )
}

data class CreatePostRequest(
    val title: String,
    val content: String,
    val category: String,
) {
    fun toCommand() =
        CreatePostCommand(
            title = PostTitle.of(title),
            content = PostContent.of(content),
            category = PostCategory.valueOf(category),
        )
}

data class UpdatePostRequest(
    val title: String,
    val content: String,
    val category: String,
) {
    fun toCommand() =
        UpdatePostCommand(
            title = PostTitle.of(title),
            content = PostContent.of(content),
            category = PostCategory.valueOf(category),
        )
}