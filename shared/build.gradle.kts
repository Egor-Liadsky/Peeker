import com.codingfeline.buildkonfig.compiler.FieldSpec.Type

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true

            export(libs.decompose)
            export(libs.essenty.lifecycle)
        }
    }

    sourceSets {

        commonMain.dependencies {
            api(libs.decompose)
            api(libs.essenty.lifecycle)

            implementation(libs.paging)
            implementation(libs.bundles.ktor.common)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.androidx.datastore.preferences)
            implementation("app.cash.paging:paging-common:3.3.0-alpha02-0.5.1")

            implementation(libs.sqldelight.runtime)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.sqldelight.android.driver)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }
    }
}

buildkonfig {
    packageName = "com.lyadsky.peeker"

    defaultConfigs {
        buildConfigField(Type.STRING, "BASE_URL", "http://62.109.29.83:7812/api/")
        buildConfigField(Type.STRING, "APP_VERSION", libs.versions.versionName.get())

        buildConfigField(Type.INT, "PAGING_OFFSET", "30")
        buildConfigField(Type.INT, "PAGING_INITIAL_PAGE", "0")

        buildConfigField(Type.STRING, "VK_GROUP", "https://vk.com/")
        buildConfigField(Type.STRING, "TELEGRAM_GROUP", "https://web.telegram.org/k/")
        buildConfigField(Type.STRING, "FEEDBACK_EMAIL", "support@peeker.me")
        buildConfigField(Type.STRING, "FAQ_EMAIL", "admin@peeker.me")
    }
}

android {
    namespace = "com.lyadsky.peeker"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.lyadsky")
        }
    }
}
