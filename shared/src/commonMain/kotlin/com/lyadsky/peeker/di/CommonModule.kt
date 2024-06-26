package com.lyadsky.peeker.di

import com.lyadsky.peeker.data.network.repository.HomeRepository
import com.lyadsky.peeker.data.network.service.HomeService
import com.lyadsky.peeker.data.database.MarketRepository
import com.lyadsky.peeker.data.network.repository.ProductRepositoryPagingSource
import com.lyadsky.peeker.data.storage.Storage
import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule(), platformModule())
    }

// called by iOS
fun initKoin() = initKoin {}

fun commonModule(): Module = module {
    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }
    single {
        HttpClient(get()) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Ktor: $message")
                    }
                }
                level = LogLevel.ALL
            }
        }
    }

    // Repository
    single { HomeRepository(get()) }
    single { MarketRepository(get()) }
    single { ProductRepositoryPagingSource(get(), get()) }

    // Services
    single { HomeService(get(), get(), get()) }

    // Storage
    single { Storage(get()) }
}