package cherhy.example.api

import cherhy.example.domain.UserEmail
import cherhy.example.domain.UserPassword
import cherhy.example.domain.Username

data class SignUpRequest(
    val email: UserEmail,
    val password: UserPassword,
    val confirmPassword: UserPassword,
    val name: Username,
) {
    init {
        require(password == confirmPassword) { "password and confirmPassword must be same" }
    }
}

data class UserUpdateRequest(
    val email: UserEmail,
    val password: UserPassword,
    val confirmPassword: UserPassword,
) {
    init {
        require(password == confirmPassword) { "password and confirmPassword must be same" }
    }
}