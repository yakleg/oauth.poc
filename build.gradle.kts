plugins {
    java
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.1.4.RELEASE") {
        exclude(module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-jetty:2.1.4.RELEASE")

    implementation("org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure:2.1.4.RELEASE")

    implementation("org.springframework.cloud:spring-cloud-security:2.1.1.RELEASE")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:2.1.1.RELEASE")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-ribbon:2.1.1.RELEASE")

    implementation("io.github.openfeign:feign-okhttp:10.2.0")
    implementation("io.github.openfeign:feign-httpclient:10.2.0")

    testCompile("org.springframework.boot:spring-boot-starter-test:2.1.4.RELEASE")
}
