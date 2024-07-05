package com.cherhy.plugins.util.model

//import java.time.LocalDateTime
//
//abstract class BaseLongIdTable<T>(
//    name: String,
//    type: T,
//): LongIdTable(name, idName) {
//    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
//    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }
//}
//
//abstract class BaseEntity(
//    id: EntityID<Long>,
//    table: BaseLongIdTable,
//): LongEntity(id) {
//    val createdAt by table.createdAt
//    var updatedAt by table.updatedAt
//}
//
//abstract class BaseEntityClass<E : BaseEntity>(
//    table: BaseLongIdTable,
//): LongEntityClass<E>(table) {
//    init {
//        EntityHook.subscribe { action ->
//            if (action.changeType == EntityChangeType.Updated) {
//                action.toEntity(this)?.updatedAt = LocalDateTime.now()
//            }
//        }
//    }
//}