plugins {
    java
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "cn.enaium"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

val jimmer: String by project
val mapstruct: String by project

dependencies {
    implementation("org.babyfish.jimmer:jimmer-spring-boot-starter:$jimmer")
    annotationProcessor("org.babyfish.jimmer:jimmer-apt:$jimmer")


    implementation("org.mapstruct:mapstruct:$mapstruct")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstruct")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

    implementation("cn.dev33:sa-token-spring-boot3-starter:1.34.0")

    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

java {
    sourceSets.main {
        java.srcDir("build/generated/annotationProcessor/java/main")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
