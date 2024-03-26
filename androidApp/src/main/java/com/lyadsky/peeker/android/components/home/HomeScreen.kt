package com.lyadsky.peeker.android.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.components.home.HomeComponent

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