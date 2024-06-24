package cherhy.example.plugins.service

import cherhy.example.plugins.domain.UserDomain
import cherhy.example.plugins.domain.UserEmail
import cherhy.example.plugins.repository.UserRepository
import com.cherhy.common.annotation.ReadService

@ReadService
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

    suspend fun findByEmail(email: UserEmail) =
        userRepository.findByEmail(email)
            ?.let(UserDomain::of)
            ?: throw IllegalStateException("User not found")
}