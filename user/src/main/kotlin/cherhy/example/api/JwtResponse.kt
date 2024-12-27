package cherhy.example.api

import kotlinx.serialization.Serializable

@Serializable
data class JwtResponse private constructor(
    val accessToken: String,
    val refreshToken: String,
) {
    companion object {
        @JvmStatic
        fun of(
            accessToken: String,
            refreshToken: String,
        ) = JwtResponse(accessToken, refreshToken)
    }
}