package com.cherhy.example.adapter.out.persistence

import com.cherhy.common.annotation.Mapper
import com.cherhy.example.domain.TestDomain
import com.cherhy.example.domain.TestId
import com.cherhy.example.domain.TestName
import com.cherhy.example.domain.TestStatus

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