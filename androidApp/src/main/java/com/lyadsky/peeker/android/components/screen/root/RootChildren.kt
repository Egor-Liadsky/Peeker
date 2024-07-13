package com.lyadsky.peeker.android.components.screen.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.lyadsky.peeker.android.components.bottomNavigation.BottomNavigationScreen
import com.lyadsky.peeker.android.components.screen.faq.FaqScreen
import com.lyadsky.peeker.android.components.screen.feedback.FeedbackScreen
import com.lyadsky.peeker.android.components.screen.onboarding.OnboardingScreen
import com.lyadsky.peeker.android.components.screen.privacyPolicy.PrivacyPolicyScreen
import com.lyadsky.peeker.android.components.screen.termsOfService.TermsOfServiceScreen
import com.lyadsky.peeker.components.screen.root.RootComponent

@Composable
fun RootChildren(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = stackAnimation(fade())
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.OnboardingChild -> OnboardingScreen(component = child.component)
            is RootComponent.Child.BottomNavigationChild -> BottomNavigationScreen(component = child.component)
            is RootComponent.Child.FaqChild -> FaqScreen(component = child.component)
            is RootComponent.Child.FeedbackChild -> FeedbackScreen(component = child.component)
            is RootComponent.Child.PrivacyPolicyChild -> PrivacyPolicyScreen(component = child.component)
            is RootComponent.Child.TermsOfServiceChild -> TermsOfServiceScreen(component = child.component)
        }
    }
}