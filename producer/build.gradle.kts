plugins {
    kotlin(Plugins.JVM) version PluginVersions.KOTLIN_VERSION
    id(Plugins.SPRING_BOOT) version PluginVersions.SPRING_BOOT_VERSION
    id(Plugins.DEPENDENCY_MANAGEMENT) version PluginVersions.DEPENDENCY_MANAGEMENT_VERSION
}

dependencies {
    apply(plugin = Plugins.KOTLIN_SPRING)
    apply(plugin = Plugins.DEPENDENCY_MANAGEMENT)

    implementation(project(UtilityModules.COMMON))
    implementation(Dependencies.Kafka.SPRING_KAFKA)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
    archiveFileName.set("${project.name}.jar")
}