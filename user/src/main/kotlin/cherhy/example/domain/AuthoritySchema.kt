package cherhy.example.domain

import cherhy.example.util.model.BaseEntity
import cherhy.example.util.model.BaseEntityClass
import cherhy.example.util.model.BaseLongIdTable
import org.jetbrains.exposed.dao.id.EntityID

object Authorities: BaseLongIdTable("authority", "id") {
    val role = varchar("role", 50)
    val userId = reference("user_id", Users)
}

class Authority(id: EntityID<AuthorityId>): BaseEntity(
    id = id.unwrap(),
    table = Authorities,
) {
    var role by Authorities.role
    var userId by Authorities.userId
    companion object: BaseEntityClass<Authority>(Authorities)
}

private fun EntityID<AuthorityId>.unwrap() = EntityID(value.value, Authorities)