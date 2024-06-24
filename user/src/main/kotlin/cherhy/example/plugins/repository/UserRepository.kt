package cherhy.example.plugins.repository

import cherhy.example.plugins.domain.*

interface UserRepository {
    suspend fun save(
        email: UserEmail,
        name: Username,
        password: UserPassword,
        salt: UserSalt,
    ): User

    suspend fun existsByEmail(
        email: UserEmail,
    ): Boolean

    suspend fun findByEmail(
        email: UserEmail,
    ): User?
}

class UserRepositoryImpl: UserRepository {
    override suspend fun save(
        email: UserEmail,
        name: Username,
        password: UserPassword,
        salt: UserSalt,
    ) =
        User.new {
            this.email = email.value
            this.name = name.value
            this.password = password.value
            this.salt = salt.value
        }

    override suspend fun existsByEmail(
        email: UserEmail,
    ) =
        User.find { Users.email eq email.value }.empty()

    override suspend fun findByEmail(
        email: UserEmail
    ) =
        User.find { Users.email eq email.value }.firstOrNull()
}