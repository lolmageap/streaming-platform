package com.cherhy.plugins.service

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.domain.PostCategory
import com.cherhy.plugins.domain.PostContent
import com.cherhy.plugins.domain.PostId
import com.cherhy.plugins.domain.PostTitle
import com.cherhy.plugins.repository.PostRepository

class WritePostService(
    private val postRepository: PostRepository,
) {
    suspend fun create(
        userId: UserId,
        title: PostTitle,
        content: PostContent,
        category: PostCategory,
    ) =
        postRepository.save(
            userId,
            title,
            content,
            category,
        )

    suspend fun update(
        userId: UserId,
        postId: PostId,
        title: PostTitle,
        content: PostContent,
        category: PostCategory,
    ) =
        postRepository.update(
            userId,
            postId,
            title,
            content,
            category,
        )

    suspend fun delete(
        userId: UserId,
        postId: PostId,
    ) =
        postRepository.delete(
            userId,
            postId,
        )
}