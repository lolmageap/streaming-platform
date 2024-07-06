package com.cherhy.plugins.util.model

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.datetime
import java.time.ZonedDateTime

abstract class BaseTable<T : Entity<T>>(
    name: String,
): Table<T>(name) {
    val createdAt = datetime("created_at").bindTo { ZonedDateTime.now().toLocalDateTime() }
    val updatedAt = datetime("updated_at").bindTo { ZonedDateTime.now().toLocalDateTime() }
}

interface BaseEntity<T : Entity<T>> : Entity<T> {
    val createdAt: ZonedDateTime
    var updatedAt: ZonedDateTime
}

abstract class BaseEntityFactory<T : BaseEntity<T>> : Entity.Factory<T>() {
    // TODO: update 시 updatedAt을 자동으로 업데이트되도록 구현
}