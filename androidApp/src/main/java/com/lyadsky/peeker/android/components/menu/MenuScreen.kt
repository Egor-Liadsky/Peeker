package com.lyadsky.moneychecker.android.components.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lyadsky.moneytracker.components.menu.MenuComponent

@Composable
fun MenuScreen(component: MenuComponent) {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Menu Screen")
    }
}