package com.cherhy.common.util

import com.cherhy.common.util.Stream.Post.GET_POST

object User {
    const val HOME = "/"
    private const val USER = "/users"
    const val USER_DOMAIN = "$USER/**"
    const val USER_SERVICE = "$USER-service"

    const val SIGN_UP = "$USER/signup"
    const val GET_ME = "$USER/me"
    const val UPDATE_USER = USER
    const val DELETE_USER = USER
    const val LOGIN = "$USER/login"
    const val LOGOUT = "$USER/logout"
    const val REFRESH = "$USER/refresh"
}

object Payment {
    const val HOME = "/"
    private const val PAYMENT = "/payments"
    const val PAYMENT_DOMAIN = "$PAYMENT/**"
    const val PAYMENT_SERVICE = "$PAYMENT-service"

    const val GET_PAYMENT = "$PAYMENT/{$PAYMENT_ID}"
    const val GET_PAYMENTS = PAYMENT
    const val CREATE_PAYMENT = PAYMENT
    const val UPDATE_PAYMENT = "$PAYMENT/{$PAYMENT_ID}"
    const val DELETE_PAYMENT = "$PAYMENT/{$PAYMENT_ID}"

    object Test {
        private const val TEST = "$PAYMENT/tests"

        const val GET_TEST = "$TEST/{$TEST_ID}"
        const val GET_TESTS = TEST
        const val REGISTER_TEST = TEST
        const val UPDATE_TEST = "$TEST/{$TEST_ID}"
        const val DELETE_TEST = "$TEST/{$TEST_ID}"
    }
}

object Stream {
    const val HOME = "/"
    private const val STREAM = "/streams"
    const val STREAM_DOMAIN = "$STREAM/**"
    const val STREAM_SERVICE = "$STREAM-service"

    object Post {
        private const val POST = "$STREAM/posts"

        const val GET_POST = "$POST/{$POST_ID}"
        const val GET_POSTS = POST
        const val CREATE_POST = POST
        const val UPDATE_POST = "$POST/{$POST_ID}"
        const val DELETE_POST = "$POST/{$POST_ID}"
    }

    object Video {
        const val GET_VIDEO = "$GET_POST/videos/{$VIDEO_ID}"
    }
}