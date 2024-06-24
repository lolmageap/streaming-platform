package cherhy.example.plugins.usecase

import cherhy.example.plugins.api.SignUpRequest
import cherhy.example.plugins.domain.Role
import cherhy.example.plugins.domain.UserPassword
import cherhy.example.plugins.domain.UserSalt
import cherhy.example.plugins.service.ReadUserService
import cherhy.example.plugins.service.WriteAuthorityService
import cherhy.example.plugins.service.WriteUserService
import cherhy.example.plugins.util.Encoder
import cherhy.example.plugins.util.generator.SaltGenerator
import com.cherhy.common.annotation.Facade

@Facade
class SignUpUseCase(
    private val readUserService: ReadUserService,
    private val writeUserService: WriteUserService,
    private val writeAuthorityService: WriteAuthorityService,
) {
    suspend fun execute(userRequest: SignUpRequest) {
        readUserService.ifUserExists(userRequest.email)
        val password = userRequest.password.value
        val salt = SaltGenerator.generate()
        val encodedPassword = Encoder.encodeBCrypt(password + salt)

        val user = writeUserService.createUser(
            userRequest.email,
            userRequest.name,
            UserPassword.of(encodedPassword),
            UserSalt.of(salt),
        )

        writeAuthorityService.createAuthority(user.id, Role.UNPAID_MEMBER)
    }
}