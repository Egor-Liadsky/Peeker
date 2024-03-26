package com.lyadsky.moneychecker.android.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lyadsky.moneychecker.components.home.HomeComponent

@Composable
fun HomeScreen(component: HomeComponent) {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Home Screen")
        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = component::navigateToAboutApp
        ) {
            Text(text = "Navigate to Menu Screen")
        }
    }
}