package com.lyadsky.peeker.android

import android.app.Application
import com.lyadsky.peeker.di.initKoin
import com.lyadsky.peeker.utils.ComponentFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class PeekerApp : Application(){

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.ERROR)
            androidContext(this@PeekerApp)
            allowOverride(true)

            koin.declare(ComponentFactory(koin))
        }
    }
}