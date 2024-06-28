val mainClassPath = "com.movieland.api.MovieLandApiApplicationKt"

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    mainClass.set(mainClassPath)
}

plugins {

}

dependencies {
    api(project(":domain"))
    implementation("org.springframework.boot:spring-boot-starter-web")

    // security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // spring doc(register of doc)
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
}
