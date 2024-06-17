package com.cherhy.example.application.saga

import com.cherhy.example.application.port.`in`.UpdateTestCommand
import com.cherhy.example.domain.TestId
import com.cherhy.example.domain.TestStatus
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.EventBus
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga

@Saga
class TestSaga(
    @Transient private val eventBus: EventBus,
    @Transient private val commandGateway: CommandGateway,
) {
    @StartSaga
    @SagaEventHandler(associationProperty = "name")
    fun handle(command: RegisterTestEvent) {
        val test = TestAggregate()
        SagaLifecycle.associateWith("testId", test.id)
        val updateTestCommand = UpdateTestCommand(TestId(test.id), command.name, TestStatus.fromString(test.status))
        commandGateway.send<UpdateTestCommand>(updateTestCommand)
    }
}