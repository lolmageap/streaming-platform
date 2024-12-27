package cherhy.example.api

import cherhy.example.domain.UserEmail
import cherhy.example.domain.UserPassword
import cherhy.example.domain.Username
import cherhy.example.service.UserUpdateCommand
import cherhy.example.usecase.SignUpCommand

data class SignUpRequest(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val name: String,
) {
    fun toCommand() =
        SignUpCommand(
            email = UserEmail.of(email),
            password = UserPassword.of(password),
            confirmPassword = UserPassword.of(confirmPassword),
            name = Username.of(name),
        )
}

data class UserUpdateRequest(
    val email: String,
    val password: String,
    val confirmPassword: String,
) {
    fun toCommand() =
        UserUpdateCommand(
            email = UserEmail.of(email),
            password = UserPassword.of(password),
            confirmPassword = UserPassword.of(confirmPassword),
        )
}