package com.cherhy.plugins.service

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.domain.PostId
import com.cherhy.plugins.repository.PostRepository

class WritePostService(
    private val postRepository: PostRepository,
) {
    suspend fun create(
        userId: UserId,
    ) {
        TODO()
    }

    suspend fun update(
        userId: UserId,
        postId: PostId,
    ) {
        TODO()
    }

    suspend fun delete(
        userId: UserId,
        postId: PostId,
    ) {
        TODO()
    }
}