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

    implementation(Dependencies.Logging.KOTLIN_LOGGING)
    implementation(Dependencies.Ktor.KTOR_SERVER_CORE_JVM)
    implementation(Dependencies.Ktor.KTOR_SERVER_NETTY_JVM)
    implementation(Dependencies.Ktor.KTOR_SERIALIZATION_JACKSON_JVM)
    implementation(Dependencies.Ktor.KTOR_SERIALIZATION_KOTLINX_JSON_JVM)
    implementation(Dependencies.Ktor.KTOR_SERVER_CONFIG_YAML)
    implementation(Dependencies.Ktor.KTOR_SERVER_CALL_LOGGING_JVM)
    implementation(Dependencies.Ktor.KTOR_SERVER_SWAGGER_JVM)
    implementation(Dependencies.Ktor.KTOR_SERVER_CONTENT_NEGOTIATION_JVM)
    implementation(Dependencies.Ktor.KTOR_SERVER_STATUS_PAGES)
    implementation(Dependencies.Ktor.KTOR_KOIN)

    implementation(Dependencies.Exposed.EXPOSED_CORE)
    implementation(Dependencies.Exposed.EXPOSED_DAO)
    implementation(Dependencies.Exposed.EXPOSED_JAVA_TIME)
    implementation(Dependencies.Exposed.EXPOSED_JDBC)
    implementation(Dependencies.Exposed.EXPOSED_JSON)

    implementation(Dependencies.Database.POSTGRESQL)
    implementation(Dependencies.Database.HIKARI_CP)

    implementation(Dependencies.Security.BCRYPT)
    implementation(Dependencies.Ktor.KTOR_SERVER_AUTH_JVM)
    implementation(Dependencies.Ktor.KTOR_SERVER_AUTH_JWT_JVM)

    testImplementation(Dependencies.Test.KOTLIN_TEST_JUNIT)
    testImplementation(Dependencies.Test.KTOR_SERVER_TESTS_JVM)
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