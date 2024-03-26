package com.lyadsky.peeker.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.lyadsky.peeker.android.components.root.RootScreen
import com.lyadsky.peeker.components.root.RootComponentImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = RootComponentImpl(componentContext = defaultComponentContext())

        enableEdgeToEdge()

        setContent {
            RootScreen(component = rootComponent)
        }
    }
}