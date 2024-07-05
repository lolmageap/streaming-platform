plugins {
	kotlin("jvm") version "2.0.0"
	id("io.ktor.plugin") version "2.3.11"
}

application {
	mainClass.set("io.ktor.server.netty.EngineMain")

	val isDevelopment: Boolean = project.ext.has("development")
	applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
	implementation(project(":common"))

	implementation("io.ktor:ktor-server-core-jvm")
	implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
	implementation("io.ktor:ktor-server-config-yaml")
	implementation("io.ktor:ktor-serialization-jackson-jvm")
	implementation("io.ktor:ktor-server-call-logging-jvm")
	implementation("io.ktor:ktor-server-swagger-jvm")
	implementation("io.ktor:ktor-server-netty-jvm")
	implementation("io.ktor:ktor-server-websockets-jvm:2.3.2")
	implementation("io.ktor:ktor-server-status-pages")
	implementation("io.ktor:ktor-server-partial-content:2.3.11")
	implementation("io.github.microutils:kotlin-logging:3.0.5")

	implementation("io.minio:minio:8.5.10")
	implementation("org.postgresql:postgresql:42.7.3")
	implementation("com.zaxxer:HikariCP:5.1.0")
	implementation("io.ktor:ktor-server-websockets")

	implementation("org.slf4j:slf4j-simple:2.0.13")
	implementation("io.insert-koin:koin-ktor:3.5.6")
	implementation("org.ktorm:ktorm-core:4.0.0")

	testImplementation("io.ktor:ktor-server-tests-jvm")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
}