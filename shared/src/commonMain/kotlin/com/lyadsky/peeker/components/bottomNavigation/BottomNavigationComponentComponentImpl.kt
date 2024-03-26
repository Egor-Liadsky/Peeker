package com.lyadsky.peeker.components.bottomNavigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.bottomNavigation.BottomNavigationComponent.*
import com.lyadsky.peeker.components.chat.ChatComponentImpl
import com.lyadsky.peeker.components.home.HomeComponentImpl
import com.lyadsky.peeker.components.settings.SettingsComponentImpl
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
            MainNavTab.CHAT -> navigation.bringToFront(Config.Chat)
            MainNavTab.SETTINGS -> navigation.bringToFront(Config.Settings)
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
            Config.Chat -> chatComponent(componentContext)
            Config.Settings -> menuComponent(componentContext)
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

    private fun chatComponent(componentContext: ComponentContext): Child =
        Child.ChatChild(ChatComponentImpl(componentContext = componentContext))

    private fun menuComponent(componentContext: ComponentContext): Child =
        Child.SettingsChild(SettingsComponentImpl(componentContext = componentContext))

    @Serializable
    private sealed interface Config {

        @Serializable
        data object Home : Config

        @Serializable
        data object Chat: Config

        @Serializable
        data object Settings : Config
    }
}