package cherhy.example.domain

import org.jetbrains.exposed.sql.ResultRow

data class UserModel(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val salt: String,
    val isDeleted: Boolean,
) {
    companion object Cherhy {
        fun fromResultRow(
            row: ResultRow,
        ) =
            UserModel(
                id = row[Users.id].value,
                name = row[Users.name],
                email = row[Users.email],
                password = row[Users.password],
                salt = row[Users.salt],
                isDeleted = row[Users.isDeleted],
            )
    }
}