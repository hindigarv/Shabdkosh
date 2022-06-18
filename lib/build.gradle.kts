/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.4.2/userguide/building_java_projects.html
 */

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.5.31"
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
    signing
}

group = "io.github.hindigarv"
version = "0.1.0"

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "io.github.hindigarv"
            artifactId = "shabdkosh"
            version = "0.1.0"
            from(components["java"])
            pom {
                name.set("shabdkosh")
                description.set("A HindiGarv Shabdkosh")
                url.set("https://github.com/hindigarv/Shabdkosh")
                inceptionYear.set("2022")
                properties.set(mapOf(
                    "myProp" to "value",
                    "prop.with.dots" to "anotherValue"
                ))
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("hindigarv")
                        name.set("Hindi Garv")
                        email.set("hindi.garv.001@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/hindigarv/Shabdkosh.git")
                    developerConnection.set("scm:git:ssh://github.com/hindigarv/Shabdkosh.git")
                    url.set("https://github.com/hindigarv/Shabdkosh")
                }
            }
        }
    }
    repositories {
        maven {
            name = "ossrh"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")
            credentials {
                username = project.property("ossrh_username") as String
                password = project.property("ossrh_password") as String
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    testImplementation("org.assertj:assertj-core:3.23.1")
    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")


    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("com.google.guava:guava:30.1.1-jre")

    // To find and remove emoji from text
    implementation("com.vdurmont:emoji-java:5.1.1")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
