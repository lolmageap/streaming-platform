package com.cherhy.payment.config

import com.cherhy.payment.util.constant.DataSource.MASTER_DATASOURCE
import com.cherhy.payment.util.constant.DataSource.SLAVE_DATASOURCE
import com.cherhy.payment.util.property.R2dbcDataSourceProperty
import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Primary
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.transaction.ReactiveTransactionManager
import org.springframework.transaction.reactive.TransactionalOperator

@Configuration
@EnableR2dbcAuditing
class ConnectionFactoryConfig(
    private val r2dbcProperty: R2dbcDataSourceProperty,
) {
    @Bean
    fun slaveConnectionFactory(): ConnectionFactory {
        return ConnectionFactoryBuilder.withUrl(r2dbcProperty.slave.url)
            .username(r2dbcProperty.slave.username)
            .password(r2dbcProperty.slave.password)
            .build()
    }

    @Bean
    fun masterConnectionFactory(): ConnectionFactory {
        return ConnectionFactoryBuilder.withUrl(r2dbcProperty.master.url)
            .username(r2dbcProperty.master.username)
            .password(r2dbcProperty.master.password)
            .build()
    }

    @Bean
    @Primary
    @DependsOn("slaveConnectionFactory", "masterConnectionFactory")
    fun connectionFactory(
        @Qualifier("masterConnectionFactory") masterConnectionFactory: ConnectionFactory,
        @Qualifier("slaveConnectionFactory") slaveConnectionFactory: ConnectionFactory,
    ): ConnectionFactory {
        val factories = mapOf(
            MASTER_DATASOURCE to masterConnectionFactory,
            SLAVE_DATASOURCE to slaveConnectionFactory,
        )

        return R2dbcRoutingDataSource().apply {
            setTargetConnectionFactories(factories)
            setDefaultTargetConnectionFactory(masterConnectionFactory)
            afterPropertiesSet()
        }
    }

    @Bean
    @DependsOn("connectionFactory")
    fun reactiveTransactionManager(
        @Qualifier("connectionFactory") connectionFactory: ConnectionFactory,
    ): ReactiveTransactionManager {
        return R2dbcTransactionManager(connectionFactory)
    }

    @Bean
    @DependsOn("connectionFactory")
    fun databaseClient(
        @Qualifier("connectionFactory") connectionFactory: ConnectionFactory,
    ): DatabaseClient {
        return DatabaseClient.create(connectionFactory)
    }

    @Bean
    @DependsOn("connectionFactory")
    fun r2dbcEntityTemplate(
        @Qualifier("connectionFactory") connectionFactory: ConnectionFactory
    ): R2dbcEntityTemplate {
        return R2dbcEntityTemplate(connectionFactory)
    }

    @Bean
    @DependsOn("connectionFactory")
    fun transactionalOperator(
        @Qualifier("connectionFactory") connectionFactory: ConnectionFactory,
    ): TransactionalOperator {
        return TransactionalOperator.create(
            reactiveTransactionManager(connectionFactory)
        )
    }
}
