package cherhy.example.service

import cherhy.example.domain.UserDomain
import cherhy.example.domain.UserEmail
import cherhy.example.repository.UserRepository
import com.cherhy.common.util.model.UserId

class ReadUserService(
    private val userRepository: UserRepository,
) {
    suspend fun checkIfExists(
        email: UserEmail,
        block: () -> IllegalStateException = { IllegalStateException("User already exists") },
    ) =
        userRepository.isExists(email).let {
            if (it) block()
        }

    suspend fun get(
        email: UserEmail,
    ) =
        userRepository.findOne(email)
            ?.let(UserDomain::of)
            ?: throw IllegalStateException("User not found")

    suspend fun get(
        userId: UserId,
    ) = userRepository.findOne(userId)
        ?.let(UserDomain::of)
        ?: throw IllegalStateException("User not found")
}