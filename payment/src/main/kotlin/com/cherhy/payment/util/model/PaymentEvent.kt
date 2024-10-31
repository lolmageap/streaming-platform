package com.cherhy.payment.util.model

import java.math.BigDecimal

data class PaymentEvent(
    val id: String,
    val orderId: String,
    val amount: BigDecimal,
)