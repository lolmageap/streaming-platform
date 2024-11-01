plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.spring") version "2.0.0"
}

val coroutineVersion = "1.6.4"

dependencies {
    implementation(project(":common"))
    implementation("org.springframework.kafka:spring-kafka:3.2.3")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}