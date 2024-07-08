package com.cherhy.plugins.service

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.domain.PostId
import com.cherhy.plugins.repository.PostRepository

class ReadPostService(
    private val postRepository: PostRepository,
) {
    suspend fun get(
        userId: UserId,
        postId: PostId,
    ) {
        TODO()
    }
}