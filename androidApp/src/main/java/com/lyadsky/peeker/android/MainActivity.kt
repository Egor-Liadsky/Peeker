package com.lyadsky.peeker.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.lyadsky.peeker.android.components.screen.root.RootScreen
import com.lyadsky.peeker.di.createRootComponent
import com.lyadsky.peeker.utils.ComponentFactory
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val componentFactory = application.get<ComponentFactory>()
        val rootComponent = componentFactory.createRootComponent(defaultComponentContext())

        enableEdgeToEdge()

        setContent {
            RootScreen(component = rootComponent)
        }
    }
}