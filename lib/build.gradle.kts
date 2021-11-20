import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    `java-library`
    `maven-publish`
    signing
    kotlin(Kotlin.jvmId)
    kotlin(Kotlin.kaptId)
    dokka(Kotlin.dokkaId) version Kotlin.version
}

description = "A Java Swing Component to choose a font according to the list of available font families, styles and sizes."
group = "io.github.kolod"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(Kotlin.stdlibJdk8)
    implementation(Slf4j.core)
    dokkaPlugin(Kotlin.dokka)
}

java.withSourcesJar()

tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets {
        named("main") {
            moduleName.set("fontchooser")
        }
    }
}

val dokkaJavadocJar by tasks.register<Jar>("dokkaJavadocJar") {
    dependsOn(tasks.dokkaJavadoc)
    from(tasks.dokkaJavadoc.flatMap { it.outputDirectory })
    archiveClassifier.set("javadoc")
}

val dokkaHtmlJar by tasks.register<Jar>("dokkaHtmlJar") {
    dependsOn(tasks.dokkaHtml)
    from(tasks.dokkaHtml.flatMap { it.outputDirectory })
    archiveClassifier.set("html-doc")
}

publishing {
    repositories {
        maven {
            val releasesRepoUrl = "https://s01.oss.sonatype.org/content/repositories/releases/"
            val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            val ossrhUsername: String by project
            val ossrhPassword: String by project

            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            credentials {
                username = ossrhUsername
                password = ossrhPassword
            }
        }
    }
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
            version = "2.4"
            groupId = "io.github.kolod"
            artifactId = "fontchooser"
            artifact(dokkaJavadocJar)
            artifact(dokkaHtmlJar)
        }
        create<MavenPublication>("mavenJava") {
            pom {
                name.set("fontchooser")
                description.set("A Java Swing Component to choose a font according to the list of available font families, styles and sizes.")
                url.set("https://github.com/kolod/fontchooser")
                licenses {
                    license {
                        name.set("GNU Lesser General Public License Version 3")
                        url.set("https://raw.githubusercontent.com/kolod/fontchooser/master/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("dheid")
                        name.set("Daniel Heid")
                        email.set("dheid@posteo.de")
                    }
                    developer {
                        id.set("Kolod")
                        name.set("Oleksandr Kolodkin")
                        email.set("alexandr.kolodkin@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/kolod/fontchooser.git")
                    developerConnection.set("scm:git:ssh:git@github.com:kolod/fontchooser.git")
                    url.set("https://github.com/kolod/fontchooser")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = Jvm.version
}
