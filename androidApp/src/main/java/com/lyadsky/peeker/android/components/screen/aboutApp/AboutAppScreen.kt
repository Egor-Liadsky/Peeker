package com.lyadsky.peeker.android.components.screen.aboutApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.components.screen.aboutApp.AboutAppComponent

@Composable
fun AboutAppScreen(component: AboutAppComponent) {

    Column(Modifier.fillMaxSize()) {
        Text(text = "About App Screen")
        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = { component.onBackButtonClick() }
        ) {
            Text(text = "Back")
        }
    }
}