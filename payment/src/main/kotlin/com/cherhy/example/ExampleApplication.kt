package com.cherhy.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("com.cherhy.example.util.property")
class ExampleApplication

fun main(args: Array<String>) {
    runApplication<ExampleApplication>(*args)
}
