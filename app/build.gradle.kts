plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.app.followup"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.followup"
        minSdk = 27
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

    // Compose specific options
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Compose dependencies
    implementation("androidx.compose.ui:ui:1.5.4")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.5.4")
    // Foundation (Border, Background, Box, Image, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.5.4")
    // Material Design
    implementation("androidx.compose.material:material:1.5.4")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.5.4")
    implementation("androidx.compose.material:material-icons-extended:1.5.4")
    // Integration with activities
    implementation("androidx.activity:activity-compose:1.8.1")
    // Test dependencies
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.4")
    // Debugging tool
    debugImplementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
}
