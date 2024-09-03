package com.cherhy.util.model

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.datetime
import java.time.ZonedDateTime

abstract class BaseTable<T : Entity<T>>(
    name: String,
) : Table<T>(name) {
    val createdAt = datetime("created_at").bindTo { now }
    val updatedAt = datetime("updated_at").bindTo { now }
}

interface BaseEntity<T : Entity<T>> : Entity<T> {
    val createdAt: CreatedAt
    var updatedAt: UpdatedAt
}

abstract class BaseEntityFactory<T : BaseEntity<T>> : Entity.Factory<T>() {
    // TODO: update 시 updatedAt을 자동으로 업데이트되도록 구현
}

@JvmInline
value class CreatedAt(
    val value: ZonedDateTime,
) {
    companion object {
        @JvmStatic
        fun of(
            value: ZonedDateTime,
        ) = CreatedAt(value)
    }
}

@JvmInline
value class UpdatedAt(
    val value: ZonedDateTime,
) {
    companion object {
        @JvmStatic
        fun of(
            value: ZonedDateTime,
        ) = UpdatedAt(value)
    }
}

private val now
    get() = ZonedDateTime.now().toLocalDateTime()