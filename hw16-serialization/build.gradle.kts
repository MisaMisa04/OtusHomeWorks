plugins {
    id("java")
}

group = "ru.koshkin"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    // Source: https://mvnrepository.com/artifact/tools.jackson.core/jackson-databind
    implementation("tools.jackson.core:jackson-databind:3.3.0")
    testImplementation(platform("org.junit:junit-bom:6.2.0-M1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation ("org.junit.jupiter:junit-jupiter-api")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine")
    implementation ("ch.qos.logback:logback-classic:1.6.8")
    testImplementation ("org.assertj:assertj-core:4.0.0-M1")
    testImplementation ("org.mockito:mockito-junit-jupiter:5.24.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

tasks.test {
    useJUnitPlatform()
}