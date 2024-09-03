package cherhy.example.usecase

import cherhy.example.api.SignUpRequest
import cherhy.example.plugins.reactiveTransaction
import cherhy.example.domain.Role
import cherhy.example.domain.UserPassword
import cherhy.example.domain.UserSalt
import cherhy.example.service.ReadUserService
import cherhy.example.service.WriteAuthorityService
import cherhy.example.service.WriteUserService
import cherhy.example.util.Encoder
import cherhy.example.util.SaltGenerator

class SignUpUseCase(
    private val readUserService: ReadUserService,
    private val writeUserService: WriteUserService,
    private val writeAuthorityService: WriteAuthorityService,
) {
    suspend fun execute(
        userRequest: SignUpRequest
    ) = reactiveTransaction {
        readUserService.checkIfExists(userRequest.email)
        val password = userRequest.password.value
        val salt = SaltGenerator.generate()
        val encodedPassword = Encoder.encode(password + salt)

        val user = writeUserService.create(
            userRequest.email,
            userRequest.name,
            UserPassword.of(encodedPassword),
            UserSalt.of(salt),
        )

        writeAuthorityService.createAuthority(user.id, Role.UNPAID_MEMBER)
    }
}