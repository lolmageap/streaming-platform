package com.cherhy.payment.config

import com.cherhy.payment.util.constant.DataSource.MASTER_DATASOURCE
import com.cherhy.payment.util.constant.DataSource.SLAVE_DATASOURCE
import org.springframework.r2dbc.connection.lookup.AbstractRoutingConnectionFactory
import org.springframework.transaction.reactive.TransactionSynchronizationManager
import reactor.core.publisher.Mono

class R2dbcRoutingDataSource : AbstractRoutingConnectionFactory() {
    override fun determineCurrentLookupKey(): Mono<Any> {
        return Mono.defer {
            TransactionSynchronizationManager.forCurrentTransaction().map { transactionSynchronizeManager ->
                if (transactionSynchronizeManager.isActualTransactionActive) {
                    if (transactionSynchronizeManager.isCurrentTransactionReadOnly) {
                        SLAVE_DATASOURCE
                    } else {
                        MASTER_DATASOURCE
                    }
                } else {
                    MASTER_DATASOURCE
                }
            }
        }
    }
}