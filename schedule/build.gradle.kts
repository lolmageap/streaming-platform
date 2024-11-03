plugins {
    kotlin("jvm") version "2.0.0"
    id("io.ktor.plugin") version "2.3.11"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

val exposedVersion = "0.49.0"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(project(":common"))
    implementation("io.ktor:ktor-server-core-jvm:2.3.11")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.11")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    implementation("ch.qos.logback:logback-classic:1.5.6")
    implementation("io.insert-koin:koin-ktor:3.5.6")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("com.cronutils:cron-utils:9.2.1")

    implementation("com.github.lolmageap.ktor-server-extension:scheduler:1.0.1")
    implementation("com.github.lolmageap.ktor-server-extension:exposed-shedlock:1.0.1")

    testImplementation("io.ktor:ktor-server-test-host-jvm:2.3.11")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

tasks.shadowJar {
    enabled = true
    archiveFileName.set("${project.name}.jar")

    manifest {
        attributes(
            "Main-Class" to "io.ktor.server.netty.EngineMain"
        )
    }
}

tasks.jar {
    enabled = false
}