package cherhy.example.api

import cherhy.example.domain.UserEmail
import cherhy.example.domain.UserPassword
import cherhy.example.usecase.LoginCommand

data class LoginRequest(
    val email: String,
    val password: String,
) {
    fun toCommand() =
        LoginCommand(
            email = UserEmail.of(email),
            password = UserPassword.of(password),
        )
}