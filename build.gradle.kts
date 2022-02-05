import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "me.10015388"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("junit:junit:4.13.1")
    // https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    // https://mvnrepository.com/artifact/org.mockito/mockito-all
    testImplementation("org.mockito:mockito-core:4.3.1")
    // https://mvnrepository.com/artifact/org.hamcrest/hamcrest-all
    testImplementation("org.hamcrest:hamcrest-all:1.3")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}