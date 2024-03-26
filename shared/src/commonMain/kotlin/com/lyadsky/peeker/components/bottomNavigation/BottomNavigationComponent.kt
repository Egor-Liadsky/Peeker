package com.lyadsky.moneychecker.components.bottomNavigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.lyadsky.moneychecker.components.home.HomeComponent
import com.lyadsky.moneytracker.components.menu.MenuComponent

interface BottomNavigationComponent {

    val childStack: Value<ChildStack<*, Child>>

    fun onTabClicked(tab: MainNavTab)

    fun onBackClicked(toIndex: Int)

    sealed class Child {
        class HomeChild(val component: HomeComponent): Child()
        class MenuChild(val component: MenuComponent): Child()
    }
}

enum class MainNavTab {
    HOME,
    MENU
}