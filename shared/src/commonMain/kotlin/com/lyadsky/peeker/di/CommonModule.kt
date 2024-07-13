package com.lyadsky.peeker.di

import com.lyadsky.peeker.data.network.MarketRepository
import com.lyadsky.peeker.data.network.ProductRepository
import com.lyadsky.peeker.data.paging.home.HomePaging
import com.lyadsky.peeker.data.paging.search.SearchPaging
import com.lyadsky.peeker.data.service.MarketService
import com.lyadsky.peeker.data.service.OnboardingService
import com.lyadsky.peeker.data.service.ProductService
import com.lyadsky.peeker.data.storage.OnboardingStorageRepository
import com.lyadsky.peeker.data.storage.SearchStorageRepository
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
    single { ProductRepository() }
    single { MarketRepository() }
    single { OnboardingStorageRepository(get()) }
    single { SearchStorageRepository(get()) }

    // Services
    single { ProductService(get(), get(), get()) }
    single { MarketService(get()) }
    single { OnboardingService(get()) }

    // Paging
    single { HomePaging(get()) }
    single { SearchPaging(get()) }
}