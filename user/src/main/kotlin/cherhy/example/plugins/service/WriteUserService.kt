package cherhy.example.plugins.service

import cherhy.example.plugins.domain.*
import cherhy.example.plugins.repository.UserRepository
import com.cherhy.common.annotation.WriteService

@WriteService
class WriteUserService(
    private val userRepository: UserRepository,
) {
    suspend fun createUser(
        email: UserEmail,
        name: Username,
        password: UserPassword,
        salt: UserSalt,
    ) =
        userRepository.save(email, name, password, salt)
            .let(UserDomain::of)

    suspend fun update(
        userId: UserId,
        email: UserEmail,
        password: UserPassword,
    ) =
        userRepository.save(userId, email, password)
            ?.let(UserDomain::of)
            ?: throw IllegalArgumentException("User not found")
}