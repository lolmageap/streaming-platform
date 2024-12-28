package com.cherhy.common.util.event

import java.math.BigDecimal

data class PaymentEvent(
    val id: String,
    val orderId: String,
    val amount: BigDecimal,
)