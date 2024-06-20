package com.cherhy.example.application.saga

import com.cherhy.example.application.port.`in`.DeleteTestCommand
import com.cherhy.example.application.port.`in`.RegisterTestCommand
import com.cherhy.example.application.port.`in`.UpdateTestCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.EventBus
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.springframework.beans.factory.annotation.Autowired

@Aggregate
class TestAggregate() {
    @AggregateIdentifier
    lateinit var id: String
    lateinit var name: String
    lateinit var status: String
    @Autowired @Transient private lateinit var eventBus: EventBus
    @Autowired @Transient private lateinit var commandGateway: CommandGateway

    @CommandHandler
    fun handle(command: RegisterTestCommand) {
        this.name = command.name.value

        val updateEvent = RegisterTestEvent(command.name)
        AggregateLifecycle.apply(updateEvent)
    }

    @CommandHandler
    fun handle(command: UpdateTestCommand) {
        this.id = command.id.value
        this.name = command.name.value

        val updateEvent = UpdateTestEvent(
            id = command.id,
            name = command.name,
            status = command.status,
        )

        AggregateLifecycle.apply(updateEvent)
    }

    @CommandHandler
    fun handle(command: DeleteTestCommand) {
        AggregateLifecycle.markDeleted()
    }

    @EventSourcingHandler
    fun on(event: RegisterTestEvent) {
        this.name = event.name.value
    }

    @EventSourcingHandler
    fun on(event: UpdateTestEvent) {
        this.id = event.id.value
        this.name = event.name.value
        this.status = event.status.value.name
    }

    @EventSourcingHandler
    fun on(event: DeleteTestEvent) {
        this.id = event.id.value
    }
}