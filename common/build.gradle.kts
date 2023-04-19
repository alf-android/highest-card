plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    api("androidx.core:core-ktx:1.7.0") //It needs API 33

    api("io.insert-koin:koin-android:${rootProject.extra["koinVersion"]}")
    api("io.insert-koin:koin-androidx-compose:${rootProject.extra["koinVersion"]}")

    testImplementation("junit:junit:${rootProject.extra["junitVersion"]}")
    androidTestImplementation("androidx.test.ext:junit:${rootProject.extra["testJunitVersion"]}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${rootProject.extra["testEspressoVersion"]}")
}