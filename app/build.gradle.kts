

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
}

android {
    compileSdk = Dependency.Versions.compile_sdk

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.manojyadav.assessmentnewsapp"
        minSdk = Dependency.Versions.min_sdk
        targetSdk = Dependency.Versions.target_sdk
        versionCode = Dependency.Versions.app_version_code
        versionName = Dependency.Versions.app_version_name
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                // Refer https://developer.android.com/jetpack/androidx/releases/room#compiler-options
                arguments(
                    mapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true",
                        "room.expandProjection" to "true"
                    )
                )
            }
        }
    }
    flavorDimensions("default")
    productFlavors {
        create("prod") {
            applicationId = "manojyadav.assessmentnewsapp"
        }
        create("dev") {
            applicationId = "manojyadav.assessmentnewsapp.dev"
        }
    }
    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        animationsDisabled = true
    }

    testBuildType = "debug"

    packagingOptions {
        resources.excludes.add("META-INF/*")
        resources.excludes.add(".readme")
    }

    sourceSets {
        val test by getting
        val androidTest by getting
        test.java.srcDirs("$projectDir/src/testShared")
        androidTest.java.srcDirs("$projectDir/src/testShared")
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //App Compat, layout, Core
    implementation(Dependency.AndroidX.appCompat)
    implementation(Dependency.AndroidX.constraint_layout)
    implementation(Dependency.AndroidX.ktx_core)

    //Material
    implementation(Dependency.Google.material)

    //Room
    implementation(Dependency.Room.runtime)
    implementation(Dependency.Room.ktx)
    androidTestImplementation(project(":app"))
    kapt(Dependency.Room.compiler)

    // Activity KTX
    implementation(Dependency.AndroidX.ktx_activity)

    // Lifecycle
    implementation(Dependency.Lifecycle.extensions)
    implementation(Dependency.Lifecycle.lifeCycleLiveData)
    implementation(Dependency.Lifecycle.viewmodel)
    implementation(Dependency.Lifecycle.lifeCycleRunTime)

    // Retrofit
    implementation(Dependency.Retrofit.main)
    implementation(Dependency.Retrofit.converterGSON)

    // OkHTTP
    implementation(Dependency.OkHttp.main)
    implementation(Dependency.OkHttp.logging_interceptor)

    // Coroutines
    implementation(Dependency.Coroutines.core)
    implementation(Dependency.Coroutines.android)

    //Dagger - Hilt
    implementation(Dependency.Hilt.android)
    kapt(Dependency.Hilt.android_compiler)

    //Navigation
    implementation(Dependency.Navigation.navigationFragment)
    implementation(Dependency.Navigation.navigationKtx)

    // Glide
    implementation(Dependency.Glide.runtime)
    kapt(Dependency.Glide.compiler)

    //Swipe Refresh Layout
    implementation(Dependency.SwipeRefreshLayout)

    //Idling Resource
    implementation(Dependency.AndroidX.Test.Espresso.idling_resource)

    //Testing dependencies
    testImplementation(Dependency.junit)
    testImplementation(Dependency.Test.Mockito.core)
    testImplementation(Dependency.Test.Mockito.inline)
    testImplementation(Dependency.Test.Mockito.kotlin)
    testImplementation(Dependency.AndroidX.Test.arch_core_testing)
    testImplementation(Dependency.AndroidX.Test.core)
    testImplementation(Dependency.Test.robolectric)
    testImplementation(Dependency.Test.truth)
    testImplementation(Dependency.Coroutines.test)
    testImplementation(Dependency.OkHttp.mockWebServer)
    androidTestImplementation(Dependency.AndroidX.Test.arch_core_testing)
    androidTestImplementation(Dependency.AndroidX.Test.junit)
    androidTestImplementation(Dependency.AndroidX.Test.junitKtx)
    androidTestImplementation(Dependency.AndroidX.Test.coreKtx)
    androidTestImplementation(Dependency.Test.Mockito.kotlin)
    androidTestImplementation(Dependency.Test.Mockito.dexMaker)
    androidTestImplementation(Dependency.Coroutines.test) {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-debug")
    }
    androidTestImplementation(Dependency.AndroidX.Test.Espresso.core) {
        exclude(group = "org.checkerframework", module = "checker")
    }
    androidTestImplementation(Dependency.AndroidX.Test.Espresso.contrib) {
        exclude(group = "org.checkerframework", module = "checker")
    }
    androidTestImplementation(Dependency.AndroidX.Test.Espresso.intents) {
        exclude(group = "org.checkerframework", module = "checker")
    }
    debugImplementation(Dependency.AndroidX.Test.fragmentTest) {
        exclude(group = "androidx.test", module = "monitor")
    }
    debugImplementation(Dependency.AndroidX.Test.core) {
        exclude(group = "androidx.test", module = "monitor")
    }
}