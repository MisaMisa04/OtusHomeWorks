plugins {
    id("java")
}

group = "ru.koshkin"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
//    implementation("org.ow2.asm:asm:9.11.1")
    implementation(libs.bundles.slf4j)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

}