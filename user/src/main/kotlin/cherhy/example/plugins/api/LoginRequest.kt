package cherhy.example.plugins.api

import cherhy.example.plugins.domain.UserEmail
import cherhy.example.plugins.domain.UserPassword

data class LoginRequest(
    val email: UserEmail,
    val password: UserPassword,
)