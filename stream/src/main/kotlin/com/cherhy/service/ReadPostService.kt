package com.cherhy.service

import com.cherhy.common.util.model.Keyword
import com.cherhy.common.util.model.Page
import com.cherhy.common.util.model.Size
import com.cherhy.common.util.model.UserId
import com.cherhy.domain.PostCategory
import com.cherhy.domain.PostId
import com.cherhy.repository.PostRepository

class ReadPostService(
    private val postRepository: PostRepository,
) {
    suspend fun ifNotExist(
        userId: UserId,
        postId: PostId,
        block: () -> Exception = { NoSuchElementException("사용자의 게시물이 아닙니다.") }
    ) =
        postRepository.isExist(
            userId,
            postId,
        ).let { isExist ->
            if (isExist.not()) throw block.invoke()
        }

    suspend fun get(
        userId: UserId,
        postId: PostId,
    ) =
        postRepository.find(
            userId,
            postId,
        ) ?: throw NoSuchElementException("사용자의 게시물이 아닙니다.")

    suspend fun getAll(
        userId: UserId,
        keyword: Keyword?,
        category: PostCategory?,
        page: Page,
        size: Size,
    ) =
        postRepository.findAll(
            userId,
            keyword,
            category,
            page,
            size,
        )
}