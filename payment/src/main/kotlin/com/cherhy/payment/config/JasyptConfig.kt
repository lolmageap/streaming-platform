package com.cherhy.payment.config

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

// TODO: docker 환경 변수로 알고리즘과 비밀번호 설정해야함.
@Configuration
class JasyptConfig {
    @Bean
    @Profile("!test")
    fun jasyptStringEncryptor() =
        StandardPBEStringEncryptor().apply {
            setAlgorithm(
                System.getenv("JASYPT_ALGORITHM")
            )
            setPassword(
                System.getenv("JASYPT_PASSWORD")
            )
        }
}