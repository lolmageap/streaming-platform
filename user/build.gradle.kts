import Dependencies.Database
import Dependencies.Exposed
import Dependencies.Ktor
import Dependencies.Logging
import Dependencies.Security
import Dependencies.Test

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

    implementation(Logging.KOTLIN_LOGGING)
    implementation(Ktor.KTOR_SERVER_CORE_JVM)
    implementation(Ktor.KTOR_SERVER_NETTY_JVM)
    implementation(Ktor.KTOR_SERIALIZATION_JACKSON_JVM)
    implementation(Ktor.KTOR_SERIALIZATION_KOTLINX_JSON_JVM)
    implementation(Ktor.KTOR_SERVER_CONFIG_YAML)
    implementation(Ktor.KTOR_SERVER_CALL_LOGGING_JVM)
    implementation(Ktor.KTOR_SERVER_SWAGGER_JVM)
    implementation(Ktor.KTOR_SERVER_CONTENT_NEGOTIATION_JVM)
    implementation(Ktor.KTOR_SERVER_STATUS_PAGES)
    implementation(Ktor.KTOR_KOIN)

    implementation(Exposed.EXPOSED_CORE)
    implementation(Exposed.EXPOSED_DAO)
    implementation(Exposed.EXPOSED_JAVA_TIME)
    implementation(Exposed.EXPOSED_JDBC)
    implementation(Exposed.EXPOSED_JSON)

    implementation(Database.POSTGRESQL)
    implementation(Database.HIKARI_CP)

    implementation(Security.BCRYPT)
    implementation(Ktor.KTOR_SERVER_AUTH_JVM)
    implementation(Ktor.KTOR_SERVER_AUTH_JWT_JVM)

    testImplementation(Test.KOTLIN_TEST_JUNIT)
    testImplementation(Test.KTOR_SERVER_TESTS_JVM)
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