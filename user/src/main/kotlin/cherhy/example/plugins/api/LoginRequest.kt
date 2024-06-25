package cherhy.example.plugins.api

import cherhy.example.plugins.domain.UserEmail
import cherhy.example.plugins.domain.UserPassword

data class LoginRequest(
    val email: UserEmail,
    val password: UserPassword,
) {
    companion object {
        @JvmStatic
        fun of(
            email: UserEmail,
            password: UserPassword,
        ) = LoginRequest(
            email = email,
            password = password,
        )
    }
}