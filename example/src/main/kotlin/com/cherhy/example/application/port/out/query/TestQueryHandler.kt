package com.cherhy.example.application.port.out.query

import com.cherhy.example.application.saga.TestAggregate
import com.cherhy.example.domain.TestDomain
import com.cherhy.example.domain.TestId
import com.cherhy.example.domain.TestName
import com.cherhy.example.domain.TestStatus
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