package com.cherhy.example.adapter.out.persistence

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TestCoroutineRepository: CoroutineCrudRepository<TestR2dbcEntity, Long> {
}