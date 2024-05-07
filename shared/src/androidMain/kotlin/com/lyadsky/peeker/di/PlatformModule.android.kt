package com.lyadsky.peeker.di

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.lyadsky.AppDatabase
import com.lyadsky.peeker.utils.createDataStore
import com.lyadsky.peeker.utils.dataStoreFileName
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<HttpClientEngine> {
        Android.create { }
    }

    single {
        AppDatabase(AndroidSqliteDriver(AppDatabase.Schema, get(), "AppDatabase.db"))
    }

    single {
        createDataStore { (get() as Context).filesDir.resolve(dataStoreFileName).absolutePath }
    }
}