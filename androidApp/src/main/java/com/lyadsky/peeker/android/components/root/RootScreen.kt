package com.lyadsky.moneychecker.android.components.root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lyadsky.moneychecker.components.root.RootComponent

@Composable
fun RootScreen(component: RootComponent, modifier: Modifier = Modifier) {

    MaterialTheme {
        Surface (modifier = modifier) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                RootChildren(component = component, modifier = Modifier.weight(1f))
            }
        }
    }
}