import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.8"
    id("org.springframework.boot") version "2.6.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
}

javafx {
    version = "11.0.2"
    modules("javafx.controls", "javafx.graphics")
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

    maven {
        setUrl("https://plugins.gradle.org/m2/")
    }
}


dependencies {
    /** kotlin **/
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    /** tornadoFx */
    implementation("no.tornado:tornadofx:1.7.20")

    /** jackson kotlin **/
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")

    /** spring **/
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-integration")

    /** spring dev tool **/
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    /** log **/
    implementation("io.github.microutils:kotlin-logging:1.12.5")

    /** test **/
    testImplementation("io.projectreactor:reactor-test:3.4.13")
    testImplementation("io.kotest:kotest-extensions-spring:4.4.3")
    testImplementation("io.kotest:kotest-assertions-core:4.4.3")
    testImplementation("io.kotest:kotest-runner-junit5:4.4.3")
    testImplementation("com.ninja-squad:springmockk:3.0.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.mockk:mockk:1.12.2")
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
