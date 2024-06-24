package cherhy.example.plugins.usecase

import cherhy.example.plugins.api.LoginRequest
import cherhy.example.plugins.service.ReadAuthorityService
import cherhy.example.plugins.service.ReadUserService
import cherhy.example.plugins.util.Encoder
import cherhy.example.plugins.util.JwtManager
import com.cherhy.common.annotation.Facade

@Facade
class LoginUseCase(
    private val readUserService: ReadUserService,
    private val readAuthorityService: ReadAuthorityService,
    private val jwtManager: JwtManager,
) {
    suspend fun execute(
        loginRequest: LoginRequest,
    ) {
        val user = readUserService.findByEmail(loginRequest.email)
        val password = loginRequest.password.value
        val salt = user.salt.value
        val encodedPassword = user.password.value
        Encoder.matchesBCrypt(password + salt, encodedPassword)

        val roles = readAuthorityService.findByUserId(user.id)
        jwtManager.createToken(user.id, user.name, roles)
    }
}