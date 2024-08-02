package cherhy.example.api

import cherhy.example.domain.UserEmail
import cherhy.example.domain.UserPassword

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