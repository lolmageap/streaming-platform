plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.spring") version "2.0.0"
}

val coroutineVersion = "1.6.4"

dependencies {
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.batch:spring-batch-core:5.1.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.smallrye.reactive:mutiny-kotlin:2.0.0")

    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql:10.15.0")
    implementation("org.springframework.restdocs:spring-restdocs-webtestclient:3.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:$coroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutineVersion")
    implementation("io.r2dbc:r2dbc-postgresql:0.8.13.RELEASE")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.testcontainers:postgresql")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.testcontainers:r2dbc")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}