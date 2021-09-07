plugins {
    id("com.diffplug.spotless") version "5.15.0"
}

spotless {
    java {
        target("**/*.java")
        importOrder()
        removeUnusedImports()
        googleJavaFormat().aosp()
    }
}
