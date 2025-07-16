plugins {
    id("com.diffplug.spotless") version "7.+"
    id("com.github.ben-manes.versions") version "+"
}

repositories { mavenCentral() }

spotless {
    java {
        target("**/*.java")
        importOrder()
        removeUnusedImports()
        googleJavaFormat().aosp()
    }

    groovyGradle {
        target("**/*.gradle")
        greclipse()
    }

    kotlinGradle {
        target("**/*.gradle.kts")
        ktfmt()
        ktlint()
    }
}

task("resolveDependencies") {
    doLast {
        project.rootProject.allprojects.forEach { subProject ->
            subProject.buildscript.configurations.forEach { configuration ->
                if (configuration.isCanBeResolved) configuration.resolve()
            }
            subProject.configurations.forEach { configuration ->
                if (configuration.isCanBeResolved) configuration.resolve()
            }
        }
    }
}
