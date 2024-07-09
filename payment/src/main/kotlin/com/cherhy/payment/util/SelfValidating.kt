package com.cherhy.payment.util

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation

@Suppress("UNCHECKED_CAST")
abstract class SelfValidating<T> {

    private val validator by lazy { Validation.buildDefaultValidatorFactory().validator }

    init {
        validateSelf()
    }

    private fun validateSelf() {
        val violations = validator.validate(this as T)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}