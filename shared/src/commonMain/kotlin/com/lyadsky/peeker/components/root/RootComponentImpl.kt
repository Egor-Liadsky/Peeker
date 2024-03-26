package com.lyadsky.moneychecker.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.lyadsky.moneychecker.components.aboutApp.AboutAppComponentImpl
import com.lyadsky.moneychecker.components.bottomNavigation.BottomNavigationComponentComponentImpl
import kotlinx.serialization.Serializable


class RootComponentImpl(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.BottomNavigation,
            childFactory = ::childFactory,
        )

    private fun childFactory(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child =
        when (config) {
            Config.BottomNavigation -> bottomNavigationComponent(componentContext)
            Config.AboutApp -> aboutAppComponent(componentContext)
        }

    private fun bottomNavigationComponent(componentContext: ComponentContext): RootComponent.Child =
        RootComponent.Child.BottomNavigationChild(
            BottomNavigationComponentComponentImpl(
                componentContext = componentContext,
                navigateToAboutAppComponent = { navigation.push(Config.AboutApp) }
            )
        )

    private fun aboutAppComponent(componentContext: ComponentContext): RootComponent.Child =
        RootComponent.Child.AboutAppChild(
            AboutAppComponentImpl(
                componentContext = componentContext,
                onBackButtonClicked = navigation::pop
            )
        )

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

    @Serializable
    private sealed interface Config {

        @Serializable
        data object BottomNavigation : Config

        @Serializable
        data object AboutApp : Config
    }
}