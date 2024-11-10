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

    implementation(Dependencies.Other.ASPECTJ_WEAVER)
    implementation(Dependencies.SpringBootStarters.SPRING_BOOT_STARTER_DATA_MONGODB)
    implementation(Dependencies.Security.JASYPT_SPRING_BOOT_STARTER)

    implementation(Dependencies.SpringBootStarters.SPRING_BOOT_STARTER_VALIDATION)
    implementation(Dependencies.Kotlin.JACKSON_MODULE_KOTLIN)
    implementation(Dependencies.Kotlin.REACTOR_KOTLIN_EXTENSIONS)
    implementation(Dependencies.Kotlin.KOTLIN_REFLECT)
    implementation(Dependencies.Other.MUTINY_KOTLIN)

    implementation(Dependencies.Kafka.SPRING_KAFKA)
    implementation(Dependencies.SpringBootStarters.SPRING_BOOT_STARTER_WEBFLUX)
    implementation(Dependencies.SpringBootStarters.SPRING_BOOT_STARTER_DATA_R2DBC)
    implementation(Dependencies.SpringBootStarters.SPRING_BOOT_STARTER_DATA_REDIS_REACTIVE)
    implementation(Dependencies.SpringBootStarters.SPRING_BOOT_STARTER_SECURITY)
    implementation(Dependencies.SpringBootStarters.SPRING_BOOT_STARTER_ACTUATOR)

    implementation(Dependencies.Axon.AXON_SPRING_BOOT_STARTER)
    implementation(Dependencies.Axon.AXON_CONFIGURATION)
    implementation(Dependencies.Test.RESTDOCS_WEBTESTCLIENT)
    implementation(Dependencies.Coroutines.KOTLIN_COROUTINES_CORE)
    implementation(Dependencies.Coroutines.KOTLIN_COROUTINES_JDK8)
    implementation(Dependencies.Coroutines.KOTLIN_COROUTINES_REACTIVE)
    implementation(Dependencies.Coroutines.KOTLIN_COROUTINES_REACTOR)

    implementation(Dependencies.Flyway.FLYWAY_CORE)
    implementation(Dependencies.Flyway.FLYWAY_DATABASE_POSTGRESQL)
    implementation(Dependencies.R2dbc.R2DBC_POSTGRESQL)
    implementation(Dependencies.R2dbc.R2DBC_POOL)
    runtimeOnly(Dependencies.Database.POSTGRESQL)
    developmentOnly(Dependencies.Other.DOCKER_COMPOSE)

    testImplementation(Dependencies.Custom.KMSL)
    testImplementation(Dependencies.Test.TESTCONTAINERS_POSTGRESQL)
    testImplementation(Dependencies.Test.REACTOR_TEST)
    testImplementation(Dependencies.Test.TESTCONTAINERS_R2DBC)
    testImplementation(Dependencies.Test.KOTEST_RUNNER_JUNIT5)
    testImplementation(Dependencies.Test.KOTEST_ASSERTIONS_CORE)
    testImplementation(Dependencies.Test.KOTEST_EXTENSIONS_SPRING)
    testImplementation(Dependencies.Test.TESTCONTAINERS_JUNIT_JUPITER)
    testImplementation(Dependencies.Test.SPRING_MOCKK)
    testImplementation(Dependencies.Test.SPRING_BOOT_STARTER_TEST)
    testImplementation(Dependencies.Test.SPRING_TESTCONTAINERS)
    testImplementation(Dependencies.Test.SPRING_SECURITY_TEST)
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