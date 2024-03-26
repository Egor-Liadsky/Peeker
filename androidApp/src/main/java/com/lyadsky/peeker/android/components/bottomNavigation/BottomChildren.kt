package com.lyadsky.moneychecker.android.components.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.lyadsky.moneychecker.android.components.home.HomeScreen
import com.lyadsky.moneychecker.android.components.menu.MenuScreen
import com.lyadsky.moneychecker.components.bottomNavigation.BottomNavigationComponent

@Composable
fun BottomNavigationChildren(
    component: BottomNavigationComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = stackAnimation(fade())
    ) {
        when(val child = it.instance) {
            is BottomNavigationComponent.Child.HomeChild -> HomeScreen(component = child.component)
            is BottomNavigationComponent.Child.MenuChild -> MenuScreen(component = child.component)
        }
    }
}