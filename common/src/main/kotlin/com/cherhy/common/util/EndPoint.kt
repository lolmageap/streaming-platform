package com.cherhy.common.util

object User {
    const val HOME = "/"
    private const val USER = "/users"
    const val USER_DOMAIN = "$USER/**"
    const val USER_SERVICE = "$USER-service"

    const val SIGN_UP = "$USER/signup"
    const val GET_ME = "$USER/me"
    const val UPDATE_USER = "$USER/{id}"
    const val DELETE_USER = "$USER/{id}"
    const val LOGIN = "$USER/login"
    const val LOGOUT = "$USER/logout"
    const val REFRESH = "$USER/refresh"
}

object Payment {
    const val HOME = "/"
    private const val PAYMENT = "/payments"
    const val PAYMENT_DOMAIN = "$PAYMENT/**"
    const val PAYMENT_SERVICE = "$PAYMENT-service"

    const val GET_PAYMENT = "$PAYMENT/{id}"
    const val GET_PAYMENTS = PAYMENT
    const val CREATE_PAYMENT = PAYMENT
    const val UPDATE_PAYMENT = "$PAYMENT/{id}"
    const val DELETE_PAYMENT = "$PAYMENT/{id}"

    object Test {
        private const val TEST = "$PAYMENT/tests"

        const val GET_TEST = "$TEST/{id}"
        const val GET_TESTS = TEST
        const val REGISTER_TEST = TEST
        const val UPDATE_TEST = "$TEST/{id}"
        const val DELETE_TEST = "$TEST/{id}"
    }
}

object Stream {
    const val HOME = "/"
    private const val STREAM = "/streams"
    const val STREAM_DOMAIN = "$STREAM/**"
    const val STREAM_SERVICE = "$STREAM-service"

    const val GET_STREAM = "$STREAM/{id}"
    const val GET_STREAMS = STREAM
    const val CREATE_STREAM = STREAM
    const val UPDATE_STREAM = "$STREAM/{id}"
    const val DELETE_STREAM = "$STREAM/{id}"
}