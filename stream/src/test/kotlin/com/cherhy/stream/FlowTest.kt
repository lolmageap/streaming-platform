package com.cherhy.stream

import io.kotest.core.spec.style.StringSpec
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout

// https://huisam.tistory.com/entry/coroutine3?category=705896 [코틀린] 코루틴 Flow 사용법
class FlowTest : StringSpec({
    "Flow 는 비동기로 데이터를 생산하는 Producer이고 Collect는 Consumer이다" {
        flow {
            repeat(3) { data ->
                delay(300L)
                emit(data)
                println("produced $data")
            }
        }.collect { data ->
            delay(700L)
            println("consumed $data")
        }
    }

    "Producer만 존재하고 Consumer가 없으면 Flow는 실행되지 않는다" {
        flow {
            repeat(3) { data ->
                delay(300L)
                emit(data)
                println("produced $data")
            }
        }
    }

    "flow는 cold stream 형태여서 발행 0.3초 + 소비 0.7초 = 1초 * 3번 = 3초가 걸린다" {
        withTimeout(2000L) {
            flow {
                repeat(3) { data ->
                    delay(300L)
                    emit(data)
                }
            }.collect {
                delay(700L)
            }
        }
    }

    "cold stream의 단점을 보완하기 위해 channel 기반의 buffer를 사용해서 속도를 개선할 수 있다" {
        withTimeout(2000L) {
            flow {
                repeat(3) { data ->
                    delay(300L)
                    emit(data)
                }
            }.buffer(10, BufferOverflow.SUSPEND)
                .collect {
                    delay(700L)
                }
        }
    }
})