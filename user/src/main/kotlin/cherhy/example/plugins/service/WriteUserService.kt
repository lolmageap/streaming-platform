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
}