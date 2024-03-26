package com.lyadsky.peeker.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.lyadsky.moneychecker.android.components.root.RootScreen
import com.lyadsky.moneychecker.components.root.RootComponentImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = RootComponentImpl(componentContext = defaultComponentContext())

        setContent {
            RootScreen(component = rootComponent)
        }
    }
}