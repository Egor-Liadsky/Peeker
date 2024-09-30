package com.lyadsky.peeker.components.bottomNavigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.screen.home.HomeComponent
import com.lyadsky.peeker.components.screen.settings.SettingsComponent

interface BottomNavigationComponent {

    val childStack: Value<ChildStack<*, Child>>

    fun onTabClicked(tab: MainNavTab)

    fun onBackClicked(toIndex: Int)

    sealed class Child {

        class HomeChild(val component: HomeComponent): Child()

//        class ChatChild(val component: ChatComponent): Child()

        class SettingsChild(val component: SettingsComponent): Child()
    }
}

enum class MainNavTab {
    HOME,

    //    CHAT,
    SETTINGS
}