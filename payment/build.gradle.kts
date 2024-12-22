import Dependencies.Axon
import Dependencies.Coroutines
import Dependencies.Database
import Dependencies.Flyway
import Dependencies.Kafka
import Dependencies.Kotlin
import Dependencies.Other
import Dependencies.R2dbc
import Dependencies.Redis
import Dependencies.Security
import Dependencies.SpringBootStarters
import Dependencies.Test
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin(Plugins.JVM) version PluginVersions.KOTLIN_VERSION
    id(Plugins.SPRING_BOOT) version PluginVersions.SPRING_BOOT_VERSION
    id(Plugins.DEPENDENCY_MANAGEMENT) version PluginVersions.DEPENDENCY_MANAGEMENT_VERSION
    id(Plugins.DOCKER_PLUGIN) version PluginVersions.DOCKER_PLUGIN_VERSION
}

dependencies {
    apply(plugin = Plugins.KOTLIN_SPRING)
    apply(plugin = Plugins.DEPENDENCY_MANAGEMENT)

    implementation(project(UtilityModules.COMMON))
    implementation(project(UtilityModules.PRODUCER))

    implementation(Other.ASPECTJ_WEAVER)
    implementation(SpringBootStarters.SPRING_BOOT_STARTER_DATA_MONGO_DB)
    implementation(Security.JASYPT_SPRING_BOOT_STARTER)

    implementation(SpringBootStarters.SPRING_BOOT_STARTER_VALIDATION)
    implementation(Kotlin.JACKSON_MODULE_KOTLIN)
    implementation(Kotlin.REACTOR_KOTLIN_EXTENSIONS)
    implementation(Kotlin.KOTLIN_REFLECT)
    implementation(Other.MUTINY_KOTLIN)

    implementation(Kafka.SPRING_KAFKA)
    implementation(SpringBootStarters.SPRING_BOOT_STARTER_WEBFLUX)
    implementation(SpringBootStarters.SPRING_BOOT_STARTER_DATA_R2DBC)
    implementation(SpringBootStarters.SPRING_BOOT_STARTER_DATA_REDIS_REACTIVE)
    implementation(SpringBootStarters.SPRING_BOOT_STARTER_SECURITY)
    implementation(SpringBootStarters.SPRING_BOOT_STARTER_ACTUATOR)

    implementation(Axon.AXON_SPRING_BOOT_STARTER)
    implementation(Axon.AXON_CONFIGURATION)
    implementation(Test.RESTDOCS_WEBTESTCLIENT)
    implementation(Coroutines.KOTLIN_COROUTINES_CORE)
    implementation(Coroutines.KOTLIN_COROUTINES_JDK8)
    implementation(Coroutines.KOTLIN_COROUTINES_REACTIVE)
    implementation(Coroutines.KOTLIN_COROUTINES_REACTOR)

    implementation(Redis.SPRING_REDISSON)
    implementation(Flyway.FLYWAY_CORE)
    implementation(Flyway.FLYWAY_DATABASE_POSTGRESQL)
    implementation(R2dbc.R2DBC_POSTGRESQL)
    implementation(R2dbc.R2DBC_POOL)
    runtimeOnly(Database.POSTGRESQL)
    developmentOnly(Other.DOCKER_COMPOSE)

    testImplementation(Dependencies.Custom.KMSL)
    testImplementation(Test.TESTCONTAINERS_POSTGRESQL)
    testImplementation(Test.REACTOR_TEST)
    testImplementation(Test.TESTCONTAINERS_R2DBC)
    testImplementation(Test.KOTEST_RUNNER_JUNIT5)
    testImplementation(Test.KOTEST_ASSERTIONS_CORE)
    testImplementation(Test.KOTEST_EXTENSIONS_SPRING)
    testImplementation(Test.TESTCONTAINERS_JUNIT_JUPITER)
    testImplementation(Test.SPRING_MOCKK)
    testImplementation(Test.SPRING_BOOT_STARTER_TEST)
    testImplementation(Test.SPRING_TESTCONTAINERS)
    testImplementation(Test.SPRING_SECURITY_TEST)
}

docker {
    val jarTask = tasks.getByName("bootJar") as BootJar
    val jarFile = jarTask.archiveFile.get().asFile
    val jarFileName = "build/libs/${jarFile.name}"
    val argument = mapOf("JAR_FILE" to jarFileName)

    name = "${rootProject.name}-${project.name}:${version}"
    docker.setDockerfile(file("../Dockerfile"))
    files(jarFile)
    buildArgs(argument)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

tasks.bootJar {
    enabled = true
    archiveFileName.set("${project.name}.jar")
}

tasks.jar {
    enabled = false
}