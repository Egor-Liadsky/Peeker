package com.lyadsky.moneychecker.components.bottomNavigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.lyadsky.moneychecker.components.bottomNavigation.BottomNavigationComponent.*
import com.lyadsky.moneychecker.components.home.HomeComponent
import com.lyadsky.moneychecker.components.home.HomeComponentImpl
import com.lyadsky.moneytracker.components.menu.MenuComponent
import com.lyadsky.moneytracker.components.menu.MenuComponentImpl
import kotlinx.serialization.Serializable

class BottomNavigationComponentComponentImpl(
    componentContext: ComponentContext,
    private val navigateToAboutAppComponent: () -> Unit
) : BottomNavigationComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Home,
            childFactory = ::childFactory
        )

    override fun onTabClicked(tab: MainNavTab) {
        when(tab) {
            MainNavTab.HOME -> navigation.bringToFront(Config.Home)
            MainNavTab.MENU -> navigation.bringToFront(Config.Menu)
        }
    }

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

    private fun childFactory(
        config: Config,
        componentContext: ComponentContext
    ): Child {
        return when (config) {
            Config.Home -> homeComponent(componentContext)
            Config.Menu -> menuComponent(componentContext)
        }
    }

    private fun homeComponent(componentContext: ComponentContext): Child =
        Child.HomeChild(
            HomeComponentImpl(
                componentContext = componentContext,
                navigateToAboutAppComponent = {
                    navigateToAboutAppComponent()
                }
            )
        )

    private fun menuComponent(componentContext: ComponentContext): Child =
        Child.MenuChild(MenuComponentImpl(componentContext = componentContext))

    @Serializable
    private sealed interface Config {

        @Serializable
        data object Home : Config

        @Serializable
        data object Menu : Config
    }
}