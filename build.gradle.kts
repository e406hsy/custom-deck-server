import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
}

group = "com.soonyong"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    /** kotlin **/
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    /** jackson kotlin **/
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")

    /** spring **/
    implementation("org.springframework.boot:spring-boot-starter-websocket")

    /** spring dev tool **/
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    /** log **/
    implementation("io.github.microutils:kotlin-logging:1.12.5")

    /** test **/
    testImplementation ("io.kotest:kotest-runner-junit5:5.0.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
