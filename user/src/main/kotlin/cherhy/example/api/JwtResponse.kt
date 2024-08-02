package cherhy.example.api

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