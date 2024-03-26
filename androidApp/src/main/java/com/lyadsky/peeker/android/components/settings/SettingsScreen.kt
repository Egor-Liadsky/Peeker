package com.lyadsky.peeker.android.components.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lyadsky.peeker.components.settings.SettingsComponent

@Composable
fun SettingsScreen(component: SettingsComponent) {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Settings Screen")
    }
}