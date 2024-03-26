package com.lyadsky.moneychecker.android.components.aboutApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lyadsky.moneychecker.components.aboutApp.AboutAppComponent

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