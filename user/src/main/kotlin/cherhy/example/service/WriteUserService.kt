package cherhy.example.service

import cherhy.example.domain.*
import cherhy.example.repository.UserRepository
import com.cherhy.common.util.model.UserId

class WriteUserService(
    private val userRepository: UserRepository,
) {
    suspend fun create(
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