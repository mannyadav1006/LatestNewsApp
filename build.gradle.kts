
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependency.gradle_plugin)
        classpath(Dependency.Kotlin.gradle_plugin)
        classpath(Dependency.Hilt.gradlePlugin)
        classpath(Dependency.Navigation.safeArgs_gradle)

    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean").configure {
    delete("build")
}