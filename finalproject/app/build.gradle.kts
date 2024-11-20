plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.ssafy.finalproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ssafy.finalproject"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    viewBinding{
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit
    // https://github.com/square/retrofit
    implementation (libs.retrofit)
    // https://github.com/square/okhttp
    implementation (libs.okhttp)
    // https://github.com/square/retrofit/tree/master/retrofit-converters/gson
    implementation (libs.converter.gson)
    // https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor
    implementation (libs.logging.interceptor)

    // Jetpack Navigation Kotlin
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)

    //framework ktx dependency 추가
    implementation (libs.androidx.fragment.ktx)

    // FCM 사용 위한 plugins
    implementation(platform(libs.firebase.bom))
    implementation (libs.firebase.messaging.ktx)

    // Beacon 사용위한 Dependency 추가
    //Android beacon Library. https://github.com/AltBeacon/android-beacon-library
    implementation (libs.android.beacon.library)

    // Glide 사용
    implementation (libs.glide)
    annotationProcessor(libs.compiler)

}