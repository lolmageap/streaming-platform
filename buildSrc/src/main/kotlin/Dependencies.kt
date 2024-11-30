object Dependencies {
    object Kotlin {
        const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"
        const val KOTLIN_STD_LIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${DependencyVersions.KOTLIN_VERSION}"
        const val JACKSON_MODULE_KOTLIN = "com.fasterxml.jackson.module:jackson-module-kotlin"
        const val REACTOR_KOTLIN_EXTENSIONS = "io.projectreactor.kotlin:reactor-kotlin-extensions"
    }

    object SpringBootStarters {
        const val SPRING_CLOUD_STARTER_GATEWAY = "org.springframework.cloud:spring-cloud-starter-gateway:${DependencyVersions.SPRING_CLOUD_STARTER_GATEWAY_VERSION}"
        const val SPRING_BOOT_STARTER_ACTUATOR = "org.springframework.boot:spring-boot-starter-actuator:${PluginVersions.SPRING_BOOT_VERSION}"
        const val SPRING_BOOT_STARTER_WEBFLUX = "org.springframework.boot:spring-boot-starter-webflux:${PluginVersions.SPRING_BOOT_VERSION}"
        const val SPRING_BOOT_STARTER_VALIDATION = "org.springframework.boot:spring-boot-starter-validation:${PluginVersions.SPRING_BOOT_VERSION}"
        const val SPRING_BOOT_STARTER_DATA_R2DBC = "org.springframework.boot:spring-boot-starter-data-r2dbc:${PluginVersions.SPRING_BOOT_VERSION}"
        const val SPRING_BOOT_STARTER_SECURITY = "org.springframework.boot:spring-boot-starter-security:${PluginVersions.SPRING_BOOT_VERSION}"
        const val SPRING_BOOT_STARTER_DATA_MONGODB = "org.springframework.boot:spring-boot-starter-data-mongodb:${PluginVersions.SPRING_BOOT_VERSION}"
        const val SPRING_BOOT_STARTER_DATA_REDIS_REACTIVE = "org.springframework.boot:spring-boot-starter-data-redis-reactive:${PluginVersions.SPRING_BOOT_VERSION}"
    }

    object Ktor {
        const val KTOR_SERVER_CORE_JVM = "io.ktor:ktor-server-core-jvm:${PluginVersions.KTOR_PLUGIN_VERSION}"
        const val KTOR_SERVER_NETTY_JVM = "io.ktor:ktor-server-netty-jvm:${PluginVersions.KTOR_PLUGIN_VERSION}"
        const val KTOR_KOIN = "io.insert-koin:koin-ktor:${DependencyVersions.KOIN_KTOR_VERSION}"
        const val KTOR_CLIENT_CIO_JVM = "io.ktor:ktor-client-cio-jvm:${PluginVersions.KTOR_PLUGIN_VERSION}"
        const val KTOR_SERVER_CIO_JVM = "io.ktor:ktor-server-cio-jvm:${PluginVersions.KTOR_PLUGIN_VERSION}"
        const val KTOR_SERIALIZATION_KOTLINX_JSON_JVM = "io.ktor:ktor-serialization-kotlinx-json-jvm:${PluginVersions.KTOR_PLUGIN_VERSION}"
        const val KTOR_SERVER_CONFIG_YAML = "io.ktor:ktor-server-config-yaml:${PluginVersions.KTOR_PLUGIN_VERSION}"
        const val KTOR_SERIALIZATION_JACKSON_JVM = "io.ktor:ktor-serialization-jackson-jvm:${PluginVersions.KTOR_PLUGIN_VERSION}"
        const val KTOR_SERVER_CALL_LOGGING_JVM = "io.ktor:ktor-server-call-logging-jvm:${PluginVersions.KTOR_PLUGIN_VERSION}"
        const val KTOR_SERVER_SWAGGER_JVM = "io.ktor:ktor-server-swagger-jvm:${PluginVersions.KTOR_PLUGIN_VERSION}"
        const val KTOR_SERVER_WEBSOCKETS_JVM = "io.ktor:ktor-server-websockets-jvm:${PluginVersions.KTOR_PLUGIN_VERSION}"
        const val KTOR_SERVER_STATUS_PAGES = "io.ktor:ktor-server-status-pages:${PluginVersions.KTOR_PLUGIN_VERSION}"
        const val KTOR_SERVER_PARTIAL_CONTENT = "io.ktor:ktor-server-partial-content:${PluginVersions.KTOR_PLUGIN_VERSION}"
        const val KTOR_SERVER_CONTENT_NEGOTIATION_JVM = "io.ktor:ktor-server-content-negotiation-jvm:${PluginVersions.KTOR_PLUGIN_VERSION}"
        const val KTOR_SERVER_AUTH_JVM = "io.ktor:ktor-server-auth-jvm:${PluginVersions.KTOR_PLUGIN_VERSION}"
        const val KTOR_SERVER_AUTH_JWT_JVM = "io.ktor:ktor-server-auth-jwt-jvm:${PluginVersions.KTOR_PLUGIN_VERSION}"
    }

    object Database {
        const val HIKARI_CP = "com.zaxxer:HikariCP:${DependencyVersions.HIKARI_CP_VERSION}"
        const val POSTGRESQL = "org.postgresql:postgresql:${DependencyVersions.POSTGRESQL_VERSION}"
    }

    object Exposed {
        const val EXPOSED_CORE = "org.jetbrains.exposed:exposed-core:${DependencyVersions.EXPOSED_VERSION}"
        const val EXPOSED_DAO = "org.jetbrains.exposed:exposed-dao:${DependencyVersions.EXPOSED_VERSION}"
        const val EXPOSED_JAVA_TIME = "org.jetbrains.exposed:exposed-java-time:${DependencyVersions.EXPOSED_VERSION}"
        const val EXPOSED_JDBC = "org.jetbrains.exposed:exposed-jdbc:${DependencyVersions.EXPOSED_VERSION}"
        const val EXPOSED_JSON = "org.jetbrains.exposed:exposed-json:${DependencyVersions.EXPOSED_VERSION}"
    }

    object Ktorm {
        const val KTORM_CORE = "org.ktorm:ktorm-core:${DependencyVersions.KTORM_VERSION}"
    }

    object KMongo {
        const val KMONGO_COROUTINE_CORE = "org.litote.kmongo:kmongo-coroutine-core:${DependencyVersions.KMONGO_VERSION}"
        const val KMONGO_COROUTINE = "org.litote.kmongo:kmongo-coroutine:${DependencyVersions.KMONGO_VERSION}"
    }

    object Security {
        const val BCRYPT = "at.favre.lib:bcrypt:${DependencyVersions.BCRYPT_VERSION}"
        const val NIMBUS_JWT = "com.nimbusds:nimbus-jose-jwt:${DependencyVersions.NIMBUS_JWT_VERSION}"
        const val SPRING_SECURITY_JWT = "org.springframework.security:spring-security-jwt:${DependencyVersions.SPRING_SECURITY_JWT_VERSION}"
        const val JASYPT_SPRING_BOOT_STARTER = "com.github.ulisesbocchio:jasypt-spring-boot-starter:${DependencyVersions.JASYPT_SPRING_BOOT_STARTER_VERSION}"
    }

    object Axon {
        const val AXON_CONFIGURATION = "org.axonframework:axon-configuration:${DependencyVersions.AXON_VERSION}"
        const val AXON_SPRING_BOOT_STARTER = "org.axonframework:axon-spring-boot-starter:${DependencyVersions.AXON_VERSION}"
    }

    object R2dbc {
        const val R2DBC_POOL = "io.r2dbc:r2dbc-pool:${DependencyVersions.R2DBC_POOL_VERSION}"
        const val R2DBC_POSTGRESQL = "io.r2dbc:r2dbc-postgresql:${DependencyVersions.R2DBC_POSTGRESQL_VERSION}"
    }

    object Flyway {
        const val FLYWAY_CORE = "org.flywaydb:flyway-core:${DependencyVersions.FLYWAY_VERSION}"
        const val FLYWAY_DATABASE_POSTGRESQL = "org.flywaydb:flyway-database-postgresql:${DependencyVersions.FLYWAY_VERSION}"
    }

    object Coroutines {
        const val KOTLIN_COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${DependencyVersions.COROUTINES_VERSION}"
        const val KOTLIN_COROUTINES_JDK8 = "org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${DependencyVersions.COROUTINES_VERSION}"
        const val KOTLIN_COROUTINES_REACTIVE = "org.jetbrains.kotlinx:kotlinx-coroutines-reactive:${DependencyVersions.COROUTINES_VERSION}"
        const val KOTLIN_COROUTINES_REACTOR = "org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${DependencyVersions.COROUTINES_VERSION}"
    }

    object Test {
        const val KOTLIN_TEST_JUNIT = "org.jetbrains.kotlin:kotlin-test-junit:${DependencyVersions.KOTLIN_VERSION}"
        const val KOTEST_RUNNER_JUNIT5 = "io.kotest:kotest-runner-junit5:${DependencyVersions.JUNIT_VERSION}"
        const val KOTEST_ASSERTIONS_CORE = "io.kotest:kotest-assertions-core:${DependencyVersions.JUNIT_VERSION}"
        const val PLATFORM_LAUNCHER = "org.junit.platform:junit-platform-launcher"
        const val KOTEST_EXTENSIONS_TESTCONTAINERS = "io.kotest.extensions:kotest-extensions-testcontainers:${DependencyVersions.KOTEST_EXTENSIONS_TESTCONTAINERS_VERSION}"
        const val KOTLIN_TEST_JUNIT5 = "org.jetbrains.kotlin:kotlin-test-junit5"
        const val SPRING_BOOT_STARTER_TEST = "org.springframework.boot:spring-boot-starter-test"
        const val KOTEST_EXTENSIONS_SPRING = "io.kotest.extensions:kotest-extensions-spring:${DependencyVersions.KOTEST_SPRING_EXTENSION_VERSION}"
        const val RESTDOCS_WEBTESTCLIENT = "org.springframework.restdocs:spring-restdocs-webtestclient:${DependencyVersions.RESTDOCS_VERSION}"
        const val REACTOR_TEST = "io.projectreactor:reactor-test"
        const val TESTCONTAINERS_POSTGRESQL = "org.testcontainers:postgresql"
        const val TESTCONTAINERS_R2DBC = "org.testcontainers:r2dbc"
        const val TESTCONTAINERS_JUNIT_JUPITER = "org.testcontainers:junit-jupiter"
        const val SPRING_MOCKK = "com.ninja-squad:springmockk:${DependencyVersions.SPRING_MOCKK_VERSION}"
        const val KTOR_SERVER_TESTS_JVM = "io.ktor:ktor-server-tests-jvm"
        const val SPRING_TESTCONTAINERS = "org.springframework.boot:spring-boot-testcontainers"
        const val SPRING_SECURITY_TEST = "org.springframework.security:spring-security-test"
        const val KTOR_SERVER_TEST_HOST_JVM = "io.ktor:ktor-server-test-host-jvm:${PluginVersions.KTOR_PLUGIN_VERSION}"
    }

    object Logging {
        const val KOTLIN_LOGGING = "io.github.microutils:kotlin-logging:${DependencyVersions.KOTLIN_LOGGING_VERSION}"
        const val LOGBACK_CLASSIC = "ch.qos.logback:logback-classic:${DependencyVersions.LOGBACK_VERSION}"
    }

    object Kafka {
        const val SPRING_KAFKA = "org.springframework.kafka:spring-kafka:${DependencyVersions.SPRING_KAFKA_VERSION}"
        const val KAFKA_CLIENTS = "org.apache.kafka:kafka-clients:3.8.0"
    }

    object Custom {
        const val KMSL = "com.github.lolmageap:kmsl:1.0.2"
        const val SCHEDULER = "com.github.lolmageap.ktor-server-extension:scheduler:1.0.2"
        const val EXPOSED_SHEDLOCK = "com.github.lolmageap.ktor-server-extension:exposed-shedlock:1.0.2"
    }

    object Redis {
        const val LETTUCE_CORE = "io.lettuce.core:lettuce-core:${DependencyVersions.LETTUCE_VERSION}"
    }

    object Other {
        const val MINIO = "io.minio:minio:${DependencyVersions.MINIO_VERSION}"
        const val DOCKER_COMPOSE = "org.springframework.boot:spring-boot-docker-compose"
        const val CRON_UTILS = "com.cronutils:cron-utils:${DependencyVersions.CRON_UTILS_VERSION}"
        const val ASPECTJ_WEAVER = "org.aspectj:aspectjweaver:${DependencyVersions.ASPECTJ_WEAVER_VERSION}"
        const val MUTINY_KOTLIN = "io.smallrye.reactive:mutiny-kotlin:${DependencyVersions.KOTLIN_VERSION}"
    }
}