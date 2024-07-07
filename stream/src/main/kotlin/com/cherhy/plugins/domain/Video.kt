package com.cherhy.plugins.domain

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.util.model.BaseEntity
import com.cherhy.plugins.util.model.BaseEntityFactory
import com.cherhy.plugins.util.model.BaseTable
import org.ktorm.schema.int
import org.ktorm.schema.long
import org.ktorm.schema.varchar

object Videos : BaseTable<Video>("video") {
    val id = long("id").primaryKey().bindTo { it.id.value }
    val name = varchar("name").bindTo { it.name.value }
    val uniqueName = varchar("unique_name").bindTo { it.uniqueName.value }
    val size = int("size").bindTo { it.size.value }
    val extension = varchar("extension").bindTo { it.extension.value }
    val owner = long("owner").bindTo { it.owner.value }
    val post = long("post").bindTo { it.post.value }
}

interface Video : BaseEntity<Video> {
    companion object : BaseEntityFactory<Video>()
    val id: VideoId
    var name: VideoName
    var uniqueName: VideoUniqueName
    var size: VideoSize
    var extension: VideoExtension
    val owner: UserId
    val post: PostId
}

@JvmInline
value class VideoId(
    val value: Long,
) {
    companion object {
        @JvmStatic
        fun of(
            value: Long,
        ) = VideoId(value)
    }
}

@JvmInline
value class VideoSize(
    val value: Int,
) {
    companion object {
        @JvmStatic
        fun of(
            value: Int,
        ) = VideoSize(value)
    }
}

@JvmInline
value class VideoName(
    val value: String,
) {
    companion object {
        @JvmStatic
        fun of(
            value: String,
        ) = VideoName(value)
    }
}

@JvmInline
value class VideoUniqueName(
    val value: String,
) {
    companion object {
        @JvmStatic
        fun of(
            value: String,
        ) = VideoUniqueName(value)
    }
}

@JvmInline
value class VideoExtension(
    val value: String,
) {
    companion object {
        @JvmStatic
        fun of(
            value: String,
        ) = VideoExtension(value)
    }
}