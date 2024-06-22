package com.cherhy.payment.config

import com.cherhy.payment.util.property.R2dbcDataSourceProperty
import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class FlywayConfig(
    private val r2dbcProperty: R2dbcDataSourceProperty,
) {
    @Profile("!test")
    @Bean(initMethod = "migrate")
    fun flyway() =
        Flyway(
            Flyway.configure()
                .dataSource(
                    r2dbcProperty.master.url.replace("r2dbc:pool", "jdbc"),
                    r2dbcProperty.master.username,
                    r2dbcProperty.master.password,
                )
                .baselineOnMigrate(true)
                .locations("classpath:db/migration")
        )
}