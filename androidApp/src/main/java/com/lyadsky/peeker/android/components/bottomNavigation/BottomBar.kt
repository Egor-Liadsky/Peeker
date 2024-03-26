package com.lyadsky.peeker.android.components.bottomNavigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.theme.Color
import com.lyadsky.peeker.components.bottomNavigation.BottomNavigationComponent
import com.lyadsky.peeker.components.bottomNavigation.BottomNavigationComponent.Child
import com.lyadsky.peeker.components.bottomNavigation.MainNavTab

@Composable
fun BottomBar(component: BottomNavigationComponent, modifier: Modifier = Modifier) {

    val childStack by component.childStack.subscribeAsState()
    val activeComponent = childStack.active.instance

    Divider(
        Modifier
            .fillMaxWidth()
            .height(1.dp),
        color = Color.BottomBar.stroke
    )

    BottomNavigation(
        modifier = modifier.height(60.dp),
        backgroundColor = Color.BottomBar.background,
        elevation = 0.dp,
    ) {

        BottomNavigationItem(
            selected = activeComponent is Child.HomeChild,
            onClick = { component.onTabClicked(MainNavTab.HOME) },
            selectedContentColor = Color.BottomBar.selectedNavigationItem,
            unselectedContentColor = Color.BottomBar.unselectedNavigationItem,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = "Главная",
                    Modifier.size(24.dp)
                )
            },
        )
        BottomNavigationItem(
            selected = activeComponent is Child.ChatChild,
            onClick = { component.onTabClicked(MainNavTab.CHAT) },
            selectedContentColor = Color.BottomBar.selectedNavigationItem,
            unselectedContentColor = Color.BottomBar.unselectedNavigationItem,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chat),
                    contentDescription = "Чат",
                    Modifier.size(24.dp)
                )
            },
        )
        BottomNavigationItem(
            selected = activeComponent is Child.SettingsChild,
            onClick = { component.onTabClicked(MainNavTab.SETTINGS) },
            selectedContentColor = Color.BottomBar.selectedNavigationItem,
            unselectedContentColor = Color.BottomBar.unselectedNavigationItem,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "Настройки",
                    Modifier.size(24.dp)
                )
            },
        )
    }
}