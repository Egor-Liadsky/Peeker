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
                jvmTarget = "1.8"
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

            implementation("app.cash.paging:paging-common:3.3.0-alpha02-0.5.1")
            implementation(libs.bundles.ktor.common)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.androidx.datastore.preferences)

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
            implementation("app.cash.paging:paging-runtime-uikit:3.3.0-alpha02-0.5.1")
        }
    }
}

buildkonfig {
    packageName = "com.lyadsky.peeker"

    defaultConfigs {
        buildConfigField(Type.STRING, "BASE_URL", "http://188.165.18.190:7812/api/")
        buildConfigField(Type.STRING, "APP_VERSION", "1.0")


        buildConfigField(Type.STRING, "VK_GROUP", "https://vk.com/")
        buildConfigField(Type.STRING, "TELEGRAM_GROUP", "https://web.telegram.org/k/")

        // Pagination
        buildConfigField(Type.INT, "PAGING_OFFSET", "30")
        buildConfigField(Type.INT, "PAGING_INITIAL_PAGE", "0")
    }
}

android {
    namespace = "com.lyadsky.peeker"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.lyadsky")
        }
    }
}
