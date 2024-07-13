package com.cherhy.plugins.repository

import com.cherhy.common.util.model.*
import com.cherhy.plugins.api.PostDetailResponse
import com.cherhy.plugins.api.PostItemResponse
import com.cherhy.plugins.domain.PostCategory
import com.cherhy.plugins.domain.PostContent
import com.cherhy.plugins.domain.PostId
import com.cherhy.plugins.domain.PostTitle

interface PostRepository {
    suspend fun save(
        userId: UserId,
        title: PostTitle,
        content: PostContent,
        category: PostCategory,
    ): PostId

    suspend fun update(
        userId: UserId,
        postId: PostId,
        title: PostTitle,
        content: PostContent,
        category: PostCategory,
    )

    suspend fun delete(
        userId: UserId,
        postId: PostId,
    )

    suspend fun isExist(
        userId: UserId,
        postId: PostId,
    ): Boolean

    suspend fun find(
        userId: UserId,
        postId: PostId,
    ): PostDetailResponse?

    suspend fun find(
        userId: UserId,
        keyword: Keyword?,
        category: PostCategory?,
        page: Page,
        size: Size,
    ): PageResponse<PostItemResponse>
}

class PostRepositoryImpl : PostRepository {
    override suspend fun save(
        userId: UserId,
        title: PostTitle,
        content: PostContent,
        category: PostCategory,
    ): PostId {
        TODO("ktorm 문법에 맞게 save logic을 만들어야합니다.")
    }

    override suspend fun update(
        userId: UserId,
        postId: PostId,
        title: PostTitle,
        content: PostContent,
        category: PostCategory
    ) {
        TODO("ktorm 문법에 맞게 update logic을 만들어야합니다.")
    }

    override suspend fun delete(
        userId: UserId,
        postId: PostId,
    ) {
        TODO("ktorm 문법에 맞게 delete logic을 만들어야합니다.")
    }

    override suspend fun isExist(
        userId: UserId,
        postId: PostId,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun find(
        userId: UserId,
        postId: PostId,
    ): PostDetailResponse? {
        TODO("Not yet implemented")
    }

    override suspend fun find(
        userId: UserId,
        keyword: Keyword?,
        category: PostCategory?,
        page: Page,
        size: Size
    ): PageResponse<PostItemResponse> {
        TODO("Not yet implemented")
    }
}