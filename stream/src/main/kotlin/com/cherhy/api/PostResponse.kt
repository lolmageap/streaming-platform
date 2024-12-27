package com.cherhy.api

import com.cherhy.common.util.model.UserId
import com.cherhy.domain.*

// TODO: @Serializable error 전파 되는중인데 이거 해결해야함.
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