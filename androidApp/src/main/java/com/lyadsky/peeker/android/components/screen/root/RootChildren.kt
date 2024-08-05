package com.lyadsky.peeker.android.components.screen.root

import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.androidPredictiveBackAnimatable
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.lyadsky.peeker.android.components.bottomNavigation.BottomNavigationScreen
import com.lyadsky.peeker.android.components.screen.faq.FaqScreen
import com.lyadsky.peeker.android.components.screen.feedback.FeedbackScreen
import com.lyadsky.peeker.android.components.screen.privacyPolicy.PrivacyPolicyScreen
import com.lyadsky.peeker.android.components.screen.termsOfService.TermsOfServiceScreen
import com.lyadsky.peeker.components.screen.root.RootComponent

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootChildren(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = predictiveBackAnimation(
            backHandler = component.backHandler,
            fallbackAnimation = stackAnimation(slide(tween(500)) + fade()),
            selector = { backEvent, _, _ -> androidPredictiveBackAnimatable(backEvent) },
            onBack = component::onBackClicked
        ),
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.BottomNavigationChild -> BottomNavigationScreen(component = child.component)
            is RootComponent.Child.FaqChild -> FaqScreen(component = child.component)
            is RootComponent.Child.FeedbackChild -> FeedbackScreen(component = child.component)
            is RootComponent.Child.PrivacyPolicyChild -> PrivacyPolicyScreen(component = child.component)
            is RootComponent.Child.TermsOfServiceChild -> TermsOfServiceScreen(component = child.component)
        }
    }
}