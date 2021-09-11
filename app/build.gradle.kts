plugins {
    application
    id("io.freefair.lombok") version "6.1.0"
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("com.google.dagger:dagger:2.38.1") {
        because("compile time dependency injection framework")
    }
    annotationProcessor("com.google.dagger:dagger-compiler:2.38.1")

    implementation("com.google.guava:guava:30.1.1-jre") {
        because("ListenableFuture and other util libraries")
    }

    implementation("org.yaml:snakeyaml:1.8") {
        because("to parse members.yaml")
    }

    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
    testImplementation("com.github.stefanbirkner:system-lambda:1.2.0")
}

val mainClassSpec: String = "com.github.codingpot.github_org_member_manage_action.App"

application {
    mainClass.set(mainClassSpec)
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to mainClassSpec
        )
    }
}
