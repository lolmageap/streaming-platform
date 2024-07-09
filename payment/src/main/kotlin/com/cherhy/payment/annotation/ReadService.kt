package com.cherhy.payment.annotation

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class ReadService