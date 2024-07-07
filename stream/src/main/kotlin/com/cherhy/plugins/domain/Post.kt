package com.cherhy.plugins.domain

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.util.model.BaseEntity
import com.cherhy.plugins.util.model.BaseEntityFactory
import com.cherhy.plugins.util.model.BaseTable
import org.ktorm.schema.long
import org.ktorm.schema.varchar

object Posts : BaseTable<Post>("post") {
    val id = long("id").primaryKey().bindTo { it.id.value }
    val title = varchar("title").bindTo { it.title.value }
    val content = varchar("content").bindTo { it.content.value }
    val author = long("author").bindTo { it.author.value }
}

interface Post : BaseEntity<Post> {
    companion object : BaseEntityFactory<Post>()
    val id: PostId
    var title: PostTitle
    var content: PostContent
    val author: UserId
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