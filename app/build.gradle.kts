plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "cn.spacexc.neogram"
    compileSdk = 36

    defaultConfig {
        applicationId = "cn.spacexc.neogram"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets.getByName("main") {
        /*java.srcDirs("./src/google/java") // TODO: Huawei & FOSS editions
        java.srcDirs(
            "./jni/third_party/webrtc/rtc_base/java/src",
            "./jni/third_party/webrtc/modules/audio_device/android/java/src",
            "./jni/third_party/webrtc/sdk/android/api",
            "./jni/third_party/webrtc/sdk/android/src/java",
            "../thirdparty/WebRTC/src/java"
        )*/
        java.srcDirs(
            "./jni/third_party/webrtc/rtc_base/java/src",
            "./jni/third_party/webrtc/modules/audio_device/android/java/src",
            "./jni/third_party/webrtc/sdk/android/api",
            "./jni/third_party/webrtc/sdk/android/src/java",
            "thirdparty/WebRTC/src/java"
        )
        for (extension in arrayOf(
            "decoder_ffmpeg",
            "decoder_flac",
            "decoder_opus",
            "decoder_vp9"
        )) {
            java.srcDirs("thirdparty/androidx-media/libraries/${extension}/src/main/java")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("src/main/libs")
            jniLibs.srcDirs("../tdlib/src/main/libs")
        }
    }
    splits {
        abi {
            isEnable = true
            reset()
            include("armeabi-v7a", "arm64-v8a", "x86", "x86_64") // 你需要支持的架构
            isUniversalApk = false // 是否生成一个包含所有ABI的APK，false表示只生成拆分的
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:" + libs.versions.protobufLite.get()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.profileinstaller)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    "baselineProfile"(project(":baselineprofile"))
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(project(":tdlib"))
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.coil.gif)
    implementation(libs.accompanist.placeholder.material)
    implementation(libs.custom.qr.generator)
    implementation(libs.accompanist.drawablepainter)
    implementation(libs.commons.lang3)
    implementation(libs.dotlottie.android)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.decoder)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.zoomable)

    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.javalite)
    implementation(libs.protobuf.kotlin.lite)

    implementation(libs.relinker)

    implementation(libs.checker.qual)

    implementation(libs.compose.cloudy)
}