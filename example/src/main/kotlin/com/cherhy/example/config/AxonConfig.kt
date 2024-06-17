package com.cherhy.example.config

import com.cherhy.example.application.saga.TestAggregate
import org.axonframework.eventsourcing.EventSourcingRepository
import org.axonframework.eventsourcing.eventstore.EventStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AxonConfig {
    @Bean
    fun testAggregateEventSourcingRepository(
        eventStore: EventStore,
    ): EventSourcingRepository<TestAggregate> {
        return EventSourcingRepository.builder(TestAggregate::class.java)
            .eventStore(eventStore)
            .build()
    }
}