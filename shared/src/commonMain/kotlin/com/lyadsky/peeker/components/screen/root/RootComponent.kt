package com.lyadsky.peeker.components.screen.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.lyadsky.peeker.components.bottomNavigation.BottomNavigationComponent
import com.lyadsky.peeker.components.screen.faq.FaqComponent
import com.lyadsky.peeker.components.screen.feedback.FeedbackComponent
import com.lyadsky.peeker.components.screen.onboarding.OnboardingComponent
import com.lyadsky.peeker.components.screen.privacyPolicy.PrivacyPolicyComponent
import com.lyadsky.peeker.components.screen.termsOfService.TermsOfServiceComponent

interface RootComponent : BackHandlerOwner {

    val childStack: Value<ChildStack<*, Child>>

    fun onBackClicked()

    sealed class Child {
        data class BottomNavigationChild(val component: BottomNavigationComponent) : Child()
        data class OnboardingChild(val component: OnboardingComponent) : Child()
        data class FeedbackChild(val component: FeedbackComponent) : Child()
        data class FaqChild(val component: FaqComponent) : Child()
        data class TermsOfServiceChild(val component: TermsOfServiceComponent) : Child()
        data class PrivacyPolicyChild(val component: PrivacyPolicyComponent) : Child()
    }
}