package com.cherhy.payment.application.port.out.query

import com.cherhy.payment.application.saga.TestAggregate
import com.cherhy.payment.domain.TestDomain
import com.cherhy.payment.domain.TestId
import com.cherhy.payment.domain.TestName
import com.cherhy.payment.domain.TestStatus
import org.axonframework.eventsourcing.EventSourcingRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class TestQueryHandler(
    @Qualifier("testAggregateEventSourcingRepository") private val eventSourcingRepository: EventSourcingRepository<TestAggregate>,
) {
    suspend fun handle(
        query: FindTestQuery,
    ): TestDomain {
        val test = eventSourcingRepository.load(query.id).wrappedAggregate.aggregateRoot
        return TestDomain(
            TestId(test.id),
            TestName(test.name),
            TestStatus.fromString(test.status),
        )
    }
}