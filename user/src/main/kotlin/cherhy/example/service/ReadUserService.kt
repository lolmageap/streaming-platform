package cherhy.example.service

import cherhy.example.domain.UserDomain
import cherhy.example.domain.UserEmail
import cherhy.example.repository.UserRepository
import com.cherhy.common.util.model.UserId

class ReadUserService(
    private val userRepository: UserRepository,
) {
    suspend fun ifUserExists(
        email: UserEmail,
        block: () -> IllegalStateException = { IllegalStateException("User already exists") },
    ) =
        userRepository.existsByEmail(email).let {
            if (it) block()
        }

    suspend fun get(
        email: UserEmail,
    ) =
        userRepository.findByEmail(email)
            ?.let(UserDomain::of)
            ?: throw IllegalStateException("User not found")

    suspend fun get(
        userId: UserId,
    ) = userRepository.findById(userId)
        ?.let(UserDomain::of)
        ?: throw IllegalStateException("User not found")
}