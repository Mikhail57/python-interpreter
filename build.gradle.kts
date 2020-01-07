plugins {
    java
    kotlin("jvm") version "1.3.61"
    jacoco
    application
}

group = "ru.mustakimov.pascal"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("org.junit.jupiter", "junit-jupiter", "5.5.2")
}

application {
    mainClassName = "ru.mustakimov.pascal.MainKt"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    jacocoTestReport {
        reports {
            xml.isEnabled = true
            csv.isEnabled = false
        }
    }
    check {
        dependsOn += jacocoTestReport
    }
}