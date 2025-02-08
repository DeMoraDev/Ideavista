plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")
    //gradleSecret
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.ideavista"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ideavista"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Agregar la clave API desde gradle.properties
        manifestPlaceholders["GOOGLE_MAPS_API_KEY"] = project.findProperty("GOOGLE_MAPS_API_KEY") ?: ""

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //koin
    implementation(libs.bundles.koin)

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // Jetpack y ViewModel
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:2.0.20")

    //Navigation with Compose

    implementation("androidx.navigation:navigation-compose:2.8.5")

    //Data Store
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    //Firebase
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.8.0"))
    //Firebase products to use
    implementation("com.google.firebase:firebase-auth")
    //implementation ("com.google.android.gms:play-services-auth")

    //FireStore
    //implementation ("com.google.firebase:firebase-core")
    implementation ("com.google.firebase:firebase-firestore")
    implementation ("com.google.firebase:firebase-database")


    //Iconos extras
    implementation ("androidx.compose.material:material-icons-core:1.7.6")
    implementation ("androidx.compose.material:material-icons-extended:1.7.6")


    //Glide para imágenes
    implementation ("com.github.bumptech.glide:compose:1.0.0-beta01")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")

    //Accompanist Pager para imágenes
    implementation ("com.google.accompanist:accompanist-pager:0.30.1")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.30.1")

    //Barras de desplazamiento de Accompanist
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.30.0")

    //Separators composables
    implementation("com.composables:core:1.20.0")

    //Dependencias para GoogleMaps
    implementation ("com.google.android.gms:play-services-maps:19.0.0")
    implementation ("com.google.maps.android:maps-compose:2.14.0")
    implementation ("com.google.android.gms:play-services-location:21.3.0")
}

