package com.lyadsky.moneychecker.android.components.bottomNavigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.moneychecker.components.bottomNavigation.BottomNavigationComponent
import com.lyadsky.moneychecker.components.bottomNavigation.BottomNavigationComponent.*
import com.lyadsky.moneychecker.components.bottomNavigation.MainNavTab

@Composable
fun BottomBar(component: BottomNavigationComponent, modifier: Modifier = Modifier) {
    val childStack by component.childStack.subscribeAsState()
    val activeComponent = childStack.active.instance

    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            selected = activeComponent is Child.HomeChild,
            onClick = { component.onTabClicked(MainNavTab.HOME) },
            icon = { Icon(Icons.Filled.Home, contentDescription = "", Modifier.size(24.dp)) },
            label = { Text(text = "Home") }
        )
        NavigationBarItem(
            selected = activeComponent is Child.MenuChild,
            onClick = { component.onTabClicked(MainNavTab.MENU) },
            icon = { Icon(Icons.Default.Menu, contentDescription = "", Modifier.size(24.dp)) },
            label = { Text(text = "Menu") }
        )
    }
}