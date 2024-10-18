plugins {
    id(Dependencies.Plugins.application)
    id(Dependencies.Plugins.kotlinAndroid)
    id(Dependencies.Plugins.hilt)
    id(Dependencies.Plugins.kaptPlugin)
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
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
        sourceCompatibility = AppConfig.javaCompatibility
        targetCompatibility = AppConfig.javaCompatibility
    }

    kotlinOptions {
        jvmTarget = AppConfig.javaJvmTarget
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Android.Version.compose
    }

    testOptions {
        unitTests.apply {
            isReturnDefaultValues = true
        }
    }

}

dependencies {
    // Android
    implementation(Dependencies.Android.coreKts)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.activityCompose)
    implementation(Dependencies.Android.navigationCompose)

    implementation(Dependencies.Android.composeUi)
    implementation(Dependencies.Android.composeMaterial)
    implementation(Dependencies.Android.composeMaterialIconsExtended)
    implementation(Dependencies.Android.composeUiTooling)
    implementation(Dependencies.Android.composeUiToolingPreview)

    implementation(Dependencies.Android.lifecycleRuntimeKtx)
    implementation(Dependencies.Android.lifecycleViewModelKtx)
    implementation(Dependencies.Android.lifecycleExtensions)
    implementation(Dependencies.Android.lifecycleViewModelCompose)

    // Third Party
    implementation(Dependencies.ThirdParty.coilCompose)
    implementation(Dependencies.ThirdParty.androidMaterial)

    implementation(Dependencies.ThirdParty.kotlinxCoroutinesCore)
    implementation(Dependencies.ThirdParty.kotlinxCoroutinesAndroid)

    implementation(Dependencies.ThirdParty.retrofit)
    implementation(Dependencies.ThirdParty.retrofitConverterGson)

    implementation(Dependencies.ThirdParty.hiltAndroid)
    implementation(Dependencies.ThirdParty.hiltNavigationCompose)
    kapt(Dependencies.ThirdParty.hiltKapt)

    // Test
    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.roboeletric)
    testImplementation(Dependencies.Test.mockk)
    testImplementation(Dependencies.Test.kotlinxCoroutinesTest)
    testImplementation(Dependencies.Test.okHttp3MockWebServer)
    testImplementation(Dependencies.Test.junitJupiter)
    testImplementation(Dependencies.Test.kotlinTest)

    // Android Test
    androidTestImplementation(Dependencies.AndroidTest.junit)
    androidTestImplementation(Dependencies.AndroidTest.espressoCore)
    androidTestImplementation(Dependencies.AndroidTest.composeJunit)
    debugImplementation(Dependencies.AndroidTest.composeUiTooling)

    // Room
    implementation(Dependencies.ThirdParty.roomRuntime)
    implementation(Dependencies.ThirdParty.roomKtx)
    kapt(Dependencies.ThirdParty.roomCompiler)
}