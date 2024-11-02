plugins {
    kotlin("jvm") version "2.0.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

tasks.jar {
    enabled = true
    archiveFileName.set("${project.name}.jar")
}