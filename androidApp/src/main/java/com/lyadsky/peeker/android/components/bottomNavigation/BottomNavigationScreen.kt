package com.lyadsky.moneychecker.android.components.bottomNavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lyadsky.moneychecker.components.bottomNavigation.BottomNavigationComponent

@Composable
fun BottomNavigationScreen(component: BottomNavigationComponent) {
    Column (Modifier.fillMaxSize()) {
        BottomNavigationChildren(component = component, modifier = Modifier.weight(1f))
        BottomBar(component = component, modifier = Modifier.fillMaxWidth())
    }
}
