plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.tvloginwithgoogleapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tvloginwithgoogleapplication"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.tv.foundation)
    implementation(libs.androidx.tv.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.runtime.livedata)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

//    implementation("androidx.credentials:credentials:1.2.2")
////    implementation("androidx.credentials:credentials-play-services-auth:1.2.2")
//    implementation("com.google.android.libraries.identity.googleid:googleid:1.2.2")
//
//    implementation ("com.google.android.gms:play-services-auth:21.2.0")
//    implementation ("com.google.android.gms:play-services-task:17.2.0")
//    implementation ("com.google.android.gms:play-services-base:17.5.0")
//
//
//
//    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
//    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    implementation ("com.google.android.gms:play-services-auth:21.2.0")

    implementation("io.coil-kt:coil-compose:2.6.0")

}