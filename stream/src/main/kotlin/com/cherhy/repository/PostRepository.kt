package com.cherhy.repository

import com.cherhy.api.PostDetailResponse
import com.cherhy.api.PostItemResponse
import com.cherhy.common.util.PageOffsetCalculator
import com.cherhy.common.util.extension.noReturn
import com.cherhy.common.util.model.*
import com.cherhy.domain.*
import com.cherhy.plugins.database
import com.cherhy.util.extension.contains
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

    suspend fun isExists(
        userId: UserId,
        postId: PostId,
    ): Boolean

    suspend fun findOne(
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
        }.noReturn

    override suspend fun delete(
        userId: UserId,
        postId: PostId,
    ) =
        database.delete(Posts) {
            it.id eq postId.value
            it.author eq userId.value
        }.noReturn

    override suspend fun isExists(
        userId: UserId,
        postId: PostId,
    ) =
        database.posts.filter {
            it.id eq postId.value
            it.author eq userId.value
        }.count() > 0

    override suspend fun findOne(
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
            .also { query ->
                query.filter { it.author eq userId.value }
                keyword?.let { keyword ->
                    query.filter { it.title contains keyword.value }
                }
                category?.let { category ->
                    query.filter { it.category eq category.name }
                }
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
