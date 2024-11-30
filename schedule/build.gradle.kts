plugins {
    kotlin(Plugins.JVM) version PluginVersions.KOTLIN_VERSION
    id(Plugins.KTOR_PLUGIN) version PluginVersions.KTOR_PLUGIN_VERSION
    id(Plugins.SHADOW_JAR) version PluginVersions.SHADOW_JAR_VERSION
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(project(UtilityModules.COMMON))

    implementation(Dependencies.Ktor.KTOR_SERVER_CORE_JVM)
    implementation(Dependencies.Ktor.KTOR_SERVER_NETTY_JVM)
    implementation(Dependencies.Database.POSTGRESQL)
    implementation(Dependencies.Exposed.EXPOSED_CORE)
    implementation(Dependencies.Exposed.EXPOSED_DAO)
    implementation(Dependencies.Exposed.EXPOSED_JAVA_TIME)
    implementation(Dependencies.Exposed.EXPOSED_JDBC)

    implementation(Dependencies.Logging.LOGBACK_CLASSIC)
    implementation(Dependencies.Ktor.KTOR_KOIN)
    implementation(Dependencies.Database.HIKARI_CP)
    implementation(Dependencies.Ktor.KTOR_CLIENT_CIO_JVM)
    implementation(Dependencies.Ktor.KTOR_SERVER_CIO_JVM)

    implementation(Dependencies.Custom.SCHEDULER)
    implementation(Dependencies.Custom.EXPOSED_SHEDLOCK)

    testImplementation(Dependencies.Test.KTOR_SERVER_TEST_HOST_JVM)
    testImplementation(Dependencies.Test.KOTLIN_TEST_JUNIT)
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