plugins {
    java
    application
    groovy
    id("org.springframework.boot") version "2.2.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-batch")

    runtimeOnly("org.hsqldb:hsqldb")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.codehaus.groovy:groovy-all:2.5.8")
    testImplementation("org.spockframework:spock-spring:1.3-groovy-2.5")

    testRuntimeOnly("org.hsqldb:hsqldb")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    mainClassName = "test.imsi.ImsiApplication"
}
