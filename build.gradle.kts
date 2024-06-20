import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.spring") version "2.0.0"
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
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
        testImplementation("io.kotest:kotest-assertions-core:5.7.2")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.3")
        }
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
                freeCompilerArgs.add("-Xexport-kdoc")
                jvmTarget.set(JvmTarget.JVM_21)
            }
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}