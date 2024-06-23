package cherhy.example.plugins.domain

import cherhy.example.plugins.util.model.BaseEntity
import cherhy.example.plugins.util.model.BaseEntityClass
import cherhy.example.plugins.util.model.BaseLongIdTable
import org.jetbrains.exposed.dao.id.EntityID

object Users: BaseLongIdTable("user", "id") {
    val name = varchar("name", 50)
    val email = varchar("email", 50)
    val password = varchar("password", 50)
    val isDeleted = bool("is_deleted").default(false)
}

class User(id: EntityID<UserId>): BaseEntity(
    id = id.unwrap(),
    table = Users,
) {
    var name by Users.name
    var email by Users.email
    var password by Users.password
    var isDeleted by Users.isDeleted
    companion object: BaseEntityClass<User>(Users)
}

private fun EntityID<UserId>.unwrap() = EntityID(value.value, Users)