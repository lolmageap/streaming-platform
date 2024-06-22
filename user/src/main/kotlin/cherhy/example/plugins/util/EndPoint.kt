package cherhy.example.plugins.util

object EndPoint {
    const val HOME = "/"

    private const val USER = "/users"
    const val SIGN_UP = "$USER/signup"
    const val GET_ME = "$USER/me"
    const val UPDATE_USER = USER
    const val DELETE_USER = USER
    const val LOGIN = "$USER/login"
    const val LOGOUT = "$USER/logout"
}