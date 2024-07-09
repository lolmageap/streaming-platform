package com.cherhy.payment.adapter.out.persistence

import com.cherhy.payment.annotation.Mapper
import com.cherhy.payment.domain.TestDomain
import com.cherhy.payment.domain.TestId
import com.cherhy.payment.domain.TestName
import com.cherhy.payment.domain.TestStatus

@Mapper
class TestMapper {
    fun mapToDomainEntity(
        entity: TestR2dbcEntity,
    ) = TestDomain.generateTestDomain(
        id = TestId(entity.id.toString()),
        name = TestName(entity.name),
        status = TestStatus.fromString(entity.status),
    )
}