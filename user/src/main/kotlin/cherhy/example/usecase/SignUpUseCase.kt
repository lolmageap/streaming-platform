package cherhy.example.usecase

import cherhy.example.domain.*
import cherhy.example.plugins.reactiveTransaction
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
        command: SignUpCommand,
    ) = reactiveTransaction {
        readUserService.checkIfExists(command.email)
        val password = command.password.value
        val salt = SaltGenerator.generate()
        val encodedPassword = Encoder.encode(password + salt)

        val user = writeUserService.create(
            command.email,
            command.name,
            UserPassword.of(encodedPassword),
            UserSalt.of(salt),
        )

        writeAuthorityService.createAuthority(user.id, Role.UNPAID_MEMBER)
    }
}

data class SignUpCommand(
    val email: UserEmail,
    val password: UserPassword,
    val confirmPassword: UserPassword,
    val name: Username,
) {
    init {
        require(password == confirmPassword) { "password and confirmPassword must be same" }
    }
}