package com.cherhy.payment.adapter.out.persistence

import com.cherhy.common.annotation.PersistenceAdapter
import com.cherhy.payment.application.port.out.DeleteTestPort
import com.cherhy.payment.application.port.out.GetTestPort
import com.cherhy.payment.application.port.out.RegisterTestPort
import com.cherhy.payment.application.port.out.UpdateTestPort
import com.cherhy.payment.domain.*
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

@PersistenceAdapter
class TestPersistenceAdapter(
    private val testCoroutineRepository: TestCoroutineRepository,
    private val testMapper: TestMapper,
) : GetTestPort, RegisterTestPort, UpdateTestPort, DeleteTestPort {
    override suspend fun get(
        id: TestId,
    ): TestDomain {
        val entity = testCoroutineRepository.findById(id.value.toLong())
            ?: throw IllegalArgumentException("Test not found")

        return testMapper.mapToDomainEntity(entity)
    }

    // TODO : nullable 한 값 처리 다시하기. -> 동적 쿼리 만들기 ktorm으로 할듯
    override suspend fun get(
        name: TestName?,
        status: TestStatus?,
        pageable: Pageable,
    ): PageImpl<TestDomain> {
        val data = testCoroutineRepository.findAllByNameAndStatus(
            name = name!!.value,
            status = status!!.value.name,
            pageable = pageable,
        ).map(testMapper::mapToDomainEntity)

        val count = testCoroutineRepository.countByNameAndStatus(
            name = name.value,
            status = status.value.name,
        )

        return PageImpl(data.toList(), pageable, count)
    }

    override suspend fun create(
        name: TestName,
    ) {
        val testEntity = TestR2dbcEntity(
            name = name.value,
            status = Status.ACTIVE.name,
        )

        testCoroutineRepository.save(testEntity)
    }

    override suspend fun update(
        id: TestId,
        name: TestName,
        status: TestStatus,
    ) {
        val entity = testCoroutineRepository.findById(id.value.toLong())
            ?: throw IllegalArgumentException("Test not found")

        val updateEntity = entity.copy(
            name = name.value,
            status = status.value.name,
        )

        testCoroutineRepository.save(updateEntity)
    }

    override suspend fun delete(
        id: TestId,
    ) {
        testCoroutineRepository.deleteById(id.value.toLong())
    }
}