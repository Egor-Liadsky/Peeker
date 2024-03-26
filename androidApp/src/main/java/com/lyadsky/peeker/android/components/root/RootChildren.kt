package com.lyadsky.moneychecker.android.components.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.lyadsky.moneychecker.components.root.RootComponent
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.lyadsky.moneychecker.android.components.aboutApp.AboutAppScreen
import com.lyadsky.moneychecker.android.components.bottomNavigation.BottomNavigationScreen

@Composable
fun RootChildren(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = stackAnimation(fade())
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.BottomNavigationChild -> BottomNavigationScreen(component = child.component)
            is RootComponent.Child.AboutAppChild -> AboutAppScreen(component = child.component)
        }
    }
}