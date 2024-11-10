plugins {
    kotlin(Plugins.JVM) version PluginVersions.KOTLIN_VERSION
    id(Plugins.SPRING_BOOT) version PluginVersions.SPRING_BOOT_VERSION
    id(Plugins.DEPENDENCY_MANAGEMENT) version PluginVersions.DEPENDENCY_MANAGEMENT_VERSION
}

dependencies {
    apply(plugin = Plugins.KOTLIN_SPRING)
    apply(plugin = Plugins.DEPENDENCY_MANAGEMENT)

    implementation(project(UtilityModules.COMMON))

    implementation(Dependencies.Kotlin.KOTLIN_REFLECT)

    implementation(Dependencies.SpringBootStarters.SPRING_CLOUD_STARTER_GATEWAY)
    implementation(Dependencies.SpringBootStarters.SPRING_BOOT_STARTER_ACTUATOR)
    implementation(Dependencies.SpringBootStarters.SPRING_BOOT_STARTER_WEBFLUX)
    implementation(Dependencies.SpringBootStarters.SPRING_BOOT_STARTER_SECURITY)
    implementation(Dependencies.Security.SPRING_SECURITY_JWT)

    implementation(Dependencies.Other.MUTINY_KOTLIN)

    implementation(Dependencies.Kotlin.REACTOR_KOTLIN_EXTENSIONS)

    implementation(Dependencies.Test.RESTDOCS_WEBTESTCLIENT)

    implementation(Dependencies.Coroutines.KOTLIN_COROUTINES_CORE)
    implementation(Dependencies.Coroutines.KOTLIN_COROUTINES_JDK8)
    implementation(Dependencies.Coroutines.KOTLIN_COROUTINES_REACTIVE)
    implementation(Dependencies.Coroutines.KOTLIN_COROUTINES_REACTOR)

    implementation(Dependencies.Security.NIMBUS_JWT)

    developmentOnly(Dependencies.Other.DOCKER_COMPOSE)

    testImplementation(Dependencies.Test.KOTLIN_TEST_JUNIT)
    testImplementation(Dependencies.Test.KOTEST_RUNNER_JUNIT5)
    testImplementation(Dependencies.Test.KOTEST_ASSERTIONS_CORE)

    testImplementation(Dependencies.Test.KOTEST_EXTENSIONS_SPRING)
    testImplementation(Dependencies.Test.SPRING_BOOT_STARTER_TEST)
    testImplementation(Dependencies.Test.KOTLIN_TEST_JUNIT5)
    testImplementation(Dependencies.Test.PLATFORM_LAUNCHER)
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