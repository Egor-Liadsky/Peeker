package com.lyadsky.peeker.components.screen.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.bottomNavigation.BottomNavigationComponentComponentImpl
import com.lyadsky.peeker.components.screen.faq.FaqComponentImpl
import com.lyadsky.peeker.components.screen.feedback.FeedbackComponentImpl
import com.lyadsky.peeker.components.screen.privacyPolicy.PrivacyPolicyComponentImpl
import com.lyadsky.peeker.components.screen.termsOfService.TermsOfServiceComponentImpl
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
            Config.Faq -> faqComponent(componentContext)
            Config.Feedback -> feedbackComponent(componentContext)
            Config.PrivacyPolicy -> privacyPolicyComponent(componentContext)
            Config.TermsOfService -> termsOfServiceComponent(componentContext)
        }

    private fun bottomNavigationComponent(componentContext: ComponentContext): RootComponent.Child =
        RootComponent.Child.BottomNavigationChild(
            BottomNavigationComponentComponentImpl(
                componentContext = componentContext,
                navigateToFeedbackComponent = { navigation.push(Config.Feedback) },
                navigateToFaqComponent = { navigation.push(Config.Faq) },
                navigateToTermsOfServiceComponent = { navigation.push(Config.TermsOfService) },
                navigateToPrivacyPolicyComponent = { navigation.push(Config.PrivacyPolicy) },
            )
        )

    private fun feedbackComponent(componentContext: ComponentContext): RootComponent.Child =
        RootComponent.Child.FeedbackChild(
            FeedbackComponentImpl(
                componentContext = componentContext,
                onBackButtonClicked = navigation::pop
            )
        )

    private fun faqComponent(componentContext: ComponentContext): RootComponent.Child =
        RootComponent.Child.FaqChild(
            FaqComponentImpl(
                componentContext = componentContext,
                onBackButtonClicked = navigation::pop
            )
        )

    private fun termsOfServiceComponent(componentContext: ComponentContext): RootComponent.Child =
        RootComponent.Child.TermsOfServiceChild(
            TermsOfServiceComponentImpl(
                componentContext = componentContext,
                onBackButtonClicked = navigation::pop
            )
        )

    private fun privacyPolicyComponent(componentContext: ComponentContext): RootComponent.Child =
        RootComponent.Child.PrivacyPolicyChild(
            PrivacyPolicyComponentImpl(
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
        data object Feedback : Config

        @Serializable
        data object Faq : Config

        @Serializable
        data object TermsOfService : Config

        @Serializable
        data object PrivacyPolicy : Config
    }
}