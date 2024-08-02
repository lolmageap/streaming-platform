package com.cherhy.api

import com.cherhy.common.util.model.UserId
import com.cherhy.domain.*

data class PostDetailResponse private constructor(
    val id: PostId,
    var title: PostTitle,
    var content: PostContent,
    val author: UserId,
    val category: PostCategory,
) {
    companion object {
        fun of(
            post: Post,
        ) = with(post) {
            PostDetailResponse(
                id,
                title,
                content,
                author,
                category,
            )
        }
    }
}

data class PostItemResponse private constructor(
    val id: PostId,
    val title: PostTitle,
    val author: UserId,
    val category: PostCategory,
) {
    companion object {
        fun of(
            post: Post,
        ) = with(post) {
            PostItemResponse(
                id,
                title,
                author,
                category,
            )
        }
    }
}