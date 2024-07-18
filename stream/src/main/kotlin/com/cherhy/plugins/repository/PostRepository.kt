package com.cherhy.plugins.repository

import com.cherhy.common.util.PageOffsetCalculator
import com.cherhy.common.util.model.*
import com.cherhy.plugins.api.PostDetailResponse
import com.cherhy.plugins.api.PostItemResponse
import com.cherhy.plugins.config.database
import com.cherhy.plugins.domain.*
import com.cherhy.plugins.util.extension.toUnit
import org.ktorm.dsl.*
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

    suspend fun findAll(
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

    override suspend fun findAll(
        userId: UserId,
        keyword: Keyword?,
        category: PostCategory?,
        page: Page,
        size: Size,
    ): PageResponse<PostItemResponse> {
        val generator = PageOffsetCalculator.of(page, size)
        val expression = database.posts
            .filter { it.author eq userId.value }
            .also { query ->
                keyword?.let { query.filter { it.title like "%$it%" } }
                category?.let { query.filter { it.category.name eq it.category } }
            }

        val count = expression.count().toLong()
        val data = expression.query
            .limit(generator.offset, generator.limit)
            .map(Posts::createEntity)
            .map(PostItemResponse::of)

        return PageResponse.of(
            data = data,
            total = count,
            page = page,
            size = size,
        )
    }
}
