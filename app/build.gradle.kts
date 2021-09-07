plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
    implementation("com.google.guava:guava:30.1.1-jre")
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
