import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "2.0.0"
    id("com.palantir.docker") version "0.36.0"
}

val coroutineVersion = "1.6.4"

dependencies {
    apply(plugin = "kotlin-spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "io.spring.dependency-management")

    implementation(project(":common"))
    implementation(project(":producer"))

    implementation("org.aspectj:aspectjweaver:1.9.22.1")
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.smallrye.reactive:mutiny-kotlin:2.0.0")

    implementation("org.springframework.kafka:spring-kafka:3.2.3")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.axonframework:axon-spring-boot-starter:4.9.3")
    implementation("org.axonframework:axon-configuration:4.9.3")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.restdocs:spring-restdocs-webtestclient:3.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:$coroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutineVersion")

    implementation("org.flywaydb:flyway-core:10.15.0")
    implementation("org.flywaydb:flyway-database-postgresql:10.15.0")
    implementation("io.r2dbc:r2dbc-postgresql:0.8.13.RELEASE")
    implementation("io.r2dbc:r2dbc-pool:1.0.2.RELEASE")

    runtimeOnly("org.postgresql:postgresql")

    developmentOnly("org.springframework.boot:spring-boot-docker-compose")

    testImplementation("org.testcontainers:postgresql")
    testImplementation("com.github.lolmageap:kmsl:1.0.0")
    testImplementation("org.springframework.boot:spring-boot-starter-data-mongodb:3.3.4")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.testcontainers:r2dbc")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
}

//docker {
//    // TODO : 나 이제 BootJar 안씀 -> 아래 코드 수정 해야함 -> 근데 지금
//    val jarTask = tasks.getByName("bootJar") as BootJar
//    val jarFile = jarTask.archiveFile.get().asFile
//    val jarFileName = "build/libs/${jarFile.name}"
//    val argument = mapOf("JAR_FILE" to jarFileName)
//
//    name = "${rootProject.name}-${project.name}:${version}"
//    docker.setDockerfile(file("../Dockerfile"))
//    files(jarFile)
//    buildArgs(argument)
//}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
    archiveFileName.set("${project.name}.jar")
}