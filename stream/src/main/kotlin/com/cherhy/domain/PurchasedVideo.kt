package com.cherhy.domain

import com.cherhy.common.util.model.Price
import com.cherhy.common.util.model.UserId
import com.cherhy.util.model.BaseEntity
import com.cherhy.util.model.BaseTable
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.decimal
import org.ktorm.schema.long

object PurchasedVideos : BaseTable<PurchasedVideo>("purchased_video") {
    val id = long("id").primaryKey().bindTo { it.id.value }
    val userId = long("user_id").bindTo { it.userId.value }
    val videoId = long("video_id").bindTo { it.videoId.value }
    val price = decimal("price").bindTo { it.purchasePrice.value }
}

interface PurchasedVideo : BaseEntity<PurchasedVideo> {
    val id: PurchasedVideoId
    val videoId: VideoId
    val userId: UserId
    val purchasePrice: Price
}

@JvmInline
value class PurchasedVideoId(
    val value: Long,
) {
    companion object {
        @JvmStatic
        fun of(
            value: Long,
        ) = PurchasedVideoId(value)
    }
}

fun Any.toPurchasedVideoId() = PurchasedVideoId.of(this as Long)
val Database.purchasedVideos get() = this.sequenceOf(PurchasedVideos)