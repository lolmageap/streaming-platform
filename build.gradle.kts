import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.allopen") version "2.0.0"
    kotlin("plugin.noarg") version "2.0.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

allprojects {
    group = "com.cherhy"
    version = "0.0.1-SNAPSHOT"
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

subprojects {
    apply(plugin = "kotlin")

    dependencies {
        implementation("io.github.microutils:kotlin-logging:3.0.5")

        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
        testImplementation("io.kotest:kotest-assertions-core:5.7.2")
        testImplementation("io.kotest.extensions:kotest-extensions-testcontainers:2.0.2")
    }

    tasks.withType<KotlinJvmCompile>()
        .configureEach {
            compilerOptions
                .languageVersion
                .set(
                    org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0
                )
        }

    tasks.withType<KotlinCompile> {
        kotlin {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_21)
            }
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.jar {
        enabled = true
    }
}