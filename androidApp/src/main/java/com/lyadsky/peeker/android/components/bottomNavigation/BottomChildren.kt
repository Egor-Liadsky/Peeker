package com.lyadsky.peeker.android.components.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.lyadsky.peeker.android.components.screen.home.HomeScreen
import com.lyadsky.peeker.android.components.screen.chat.ChatScreen
import com.lyadsky.peeker.android.components.screen.settings.SettingsScreen
import com.lyadsky.peeker.components.bottomNavigation.BottomNavigationComponent

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
        when (val child = it.instance) {
            is BottomNavigationComponent.Child.HomeChild -> HomeScreen(component = child.component)
            is BottomNavigationComponent.Child.ChatChild -> ChatScreen(component = child.component)
            is BottomNavigationComponent.Child.SettingsChild -> SettingsScreen(component = child.component)
        }
    }
}