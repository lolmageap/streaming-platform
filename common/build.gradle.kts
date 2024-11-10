plugins {
    kotlin(Plugins.JVM) version PluginVersions.KOTLIN_VERSION
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

tasks.jar {
    enabled = true
    archiveFileName.set("${project.name}.jar")
}