package com.cherhy.plugins.repository

import com.cherhy.common.util.model.*
import com.cherhy.plugins.api.PostDetailResponse
import com.cherhy.plugins.api.PostItemResponse
import com.cherhy.plugins.config.database
import com.cherhy.plugins.domain.*
import com.cherhy.plugins.util.extension.toUnit
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insertAndGenerateKey
import org.ktorm.dsl.update
import org.ktorm.entity.count
import org.ktorm.entity.filter
import org.ktorm.entity.find

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
    ) =
        database.insertAndGenerateKey(Posts) {
            set(it.title, title.value)
            set(it.content, content.value)
            set(it.author, userId.value)
            set(it.category, category.name)
        }.toPostId()

    override suspend fun update(
        userId: UserId,
        postId: PostId,
        title: PostTitle,
        content: PostContent,
        category: PostCategory
    ) =
        database.update(Posts) {
            set(it.title, title.value)
            set(it.content, content.value)
            set(it.category, category.name)
            where {
                it.id eq postId.value
                it.author eq userId.value
            }
        }.toUnit()

    override suspend fun delete(
        userId: UserId,
        postId: PostId,
    ) =
        database.delete(Posts) {
            it.id eq postId.value
            it.author eq userId.value
        }.toUnit()

    override suspend fun isExist(
        userId: UserId,
        postId: PostId,
    ) =
        database.posts.filter {
            it.id eq postId.value
            it.author eq userId.value
        }.count() > 0

    override suspend fun find(
        userId: UserId,
        postId: PostId,
    ) =
        database.posts.find {
            it.id eq postId.value
            it.author eq userId.value
        }?.let(PostDetailResponse::of)

    override suspend fun find(
        userId: UserId,
        keyword: Keyword?,
        category: PostCategory?,
        page: Page,
        size: Size
    ): PageResponse<PostItemResponse> {
        TODO()
    }
}
