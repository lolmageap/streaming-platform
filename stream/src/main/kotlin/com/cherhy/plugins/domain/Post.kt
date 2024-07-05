package com.cherhy.plugins.domain

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.datetime
import org.ktorm.schema.long
import org.ktorm.schema.varchar
import java.time.ZonedDateTime

object Posts : Table<Post>("post") {
    val id = long("id").primaryKey().bindTo { it.id.value }
    val title = varchar("title").bindTo { it.title.value }
    val content = varchar("content").bindTo { it.content.value }
    val author = long("author").bindTo { it.author.value }
    val createdAt = datetime("created_at").bindTo { it.createdAt.toLocalDateTime() }
    val updatedAt = datetime("updated_at").bindTo { it.updatedAt.toLocalDateTime() }
}

interface Post : Entity<Post> {
    companion object : Entity.Factory<Post>()
    val id: PostId
    var title: PostTitle
    var content: PostContent
    val author: UserId
    val createdAt: ZonedDateTime
    val updatedAt: ZonedDateTime
}

@JvmInline
value class PostId(
    val value: Long,
) {
    companion object {
        @JvmStatic
        fun of(
            value: Long,
        ) = PostId(value)
    }
}

@JvmInline
value class PostTitle(
    val value: String,
) {
    companion object {
        @JvmStatic
        fun of(
            value: String,
        ) = PostTitle(value)
    }
}

@JvmInline
value class PostContent(
    val value: String,
) {
    companion object {
        @JvmStatic
        fun of(
            value: String,
        ) = PostContent(value)
    }
}

@JvmInline
value class UserId(
    val value: Long,
) {
    companion object {
        @JvmStatic
        fun of(
            value: Long,
        ) = UserId(value)
    }
}