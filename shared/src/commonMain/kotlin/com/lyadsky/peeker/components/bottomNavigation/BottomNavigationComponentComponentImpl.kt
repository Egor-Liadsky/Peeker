package com.lyadsky.peeker.components.bottomNavigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.bottomNavigation.BottomNavigationComponent.Child
import com.lyadsky.peeker.components.screen.chat.ChatComponentImpl
import com.lyadsky.peeker.components.screen.home.HomeComponentImpl
import com.lyadsky.peeker.components.screen.settings.SettingsComponentImpl
import kotlinx.serialization.Serializable

class BottomNavigationComponentComponentImpl(
    componentContext: ComponentContext,
    private val navigateToFeedbackComponent: () -> Unit,
    private val navigateToFaqComponent: () -> Unit,
    private val navigateToTermsOfServiceComponent: () -> Unit,
    private val navigateToPrivacyPolicyComponent: () -> Unit
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
        when (tab) {
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
            Config.Settings -> settingsComponent(componentContext)
        }
    }

    private fun homeComponent(componentContext: ComponentContext): Child =
        Child.HomeChild(
            HomeComponentImpl(
                componentContext = componentContext,
            )
        )

    private fun chatComponent(componentContext: ComponentContext): Child =
        Child.ChatChild(ChatComponentImpl(componentContext = componentContext))

    private fun settingsComponent(componentContext: ComponentContext): Child =
        Child.SettingsChild(
            SettingsComponentImpl(
                componentContext = componentContext,
                navigateToFeedbackComponent = navigateToFeedbackComponent,
                navigateToFaqComponent = navigateToFaqComponent,
                navigateToTermsOfServiceComponent = navigateToTermsOfServiceComponent,
                navigateToPrivacyPolicyComponent = navigateToPrivacyPolicyComponent,
            )
        )

    @Serializable
    private sealed interface Config {

        @Serializable
        data object Home : Config

        @Serializable
        data object Chat : Config

        @Serializable
        data object Settings : Config
    }
}