package cherhy.example.repository

import cherhy.example.domain.*
import cherhy.example.util.Encoder
import com.cherhy.common.util.model.UserId

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

    suspend fun findById(
        userId: UserId,
    ): User?
    suspend fun save(
        userId: UserId,
        email: UserEmail,
        password: UserPassword,
    ): User?
}

class UserRepositoryImpl : UserRepository {
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

    override suspend fun save(
        userId: UserId,
        email: UserEmail,
        password: UserPassword,
    ) =
        User.findByIdAndUpdate(userId.value) {
            val encodedPassword = Encoder.encode(password.value + it.salt)
            it.email = email.value
            it.password = encodedPassword
        }

    override suspend fun existsByEmail(
        email: UserEmail,
    ) =
        User.find { Users.email eq email.value }.empty()

    override suspend fun findByEmail(
        email: UserEmail
    ) =
        User.find { Users.email eq email.value }.firstOrNull()

    override suspend fun findById(
        userId: UserId,
    ) =
        User.findById(userId.value)
}