plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.spring") version "2.0.0"
}

dependencies {
    implementation("org.springframework:spring-tx:6.1.8")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
    implementation("org.aspectj:aspectjweaver:1.9.22.1")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}