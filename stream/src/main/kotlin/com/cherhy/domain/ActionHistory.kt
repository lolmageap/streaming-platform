package com.cherhy.domain

import com.cherhy.common.util.model.UserId
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.coroutine.CoroutineDatabase
import java.time.ZonedDateTime

data class ActionHistory private constructor(
    @BsonId
    val id: String? = null,
    val userId: UserId,
    val action: Action,
    val createdAt: ZonedDateTime,
) {
    companion object {
        fun of(
            userId: UserId,
            action: Action,
            createdAt: ZonedDateTime,
        ) = ActionHistory(
            userId = userId,
            action = action,
            createdAt = createdAt,
        )
    }
}

enum class Action {
    SHOW_VIDEO,
    UPDATE_VIDEO,
    DELETE_VIDEO,
}

val CoroutineDatabase.actionHistory
    get() = getCollection<ActionHistory>("action_history")