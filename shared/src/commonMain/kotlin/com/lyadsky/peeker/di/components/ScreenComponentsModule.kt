package com.lyadsky.peeker.di.components

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.bottomNavigation.BottomNavigationComponent
import com.lyadsky.peeker.components.bottomNavigation.BottomNavigationComponentComponentImpl
import com.lyadsky.peeker.components.screen.chat.ChatComponent
import com.lyadsky.peeker.components.screen.chat.ChatComponentImpl
import com.lyadsky.peeker.components.screen.faq.FaqComponent
import com.lyadsky.peeker.components.screen.faq.FaqComponentImpl
import com.lyadsky.peeker.components.screen.feedback.FeedbackComponent
import com.lyadsky.peeker.components.screen.feedback.FeedbackComponentImpl
import com.lyadsky.peeker.components.screen.home.HomeComponent
import com.lyadsky.peeker.components.screen.home.HomeComponentImpl
import com.lyadsky.peeker.components.screen.onboarding.OnboardingComponent
import com.lyadsky.peeker.components.screen.onboarding.OnboardingComponentImpl
import com.lyadsky.peeker.components.screen.privacyPolicy.PrivacyPolicyComponent
import com.lyadsky.peeker.components.screen.privacyPolicy.PrivacyPolicyComponentImpl
import com.lyadsky.peeker.components.screen.root.RootComponent
import com.lyadsky.peeker.components.screen.root.RootComponentImpl
import com.lyadsky.peeker.components.screen.settings.SettingsComponent
import com.lyadsky.peeker.components.screen.settings.SettingsComponentImpl
import com.lyadsky.peeker.components.screen.termsOfService.TermsOfServiceComponent
import com.lyadsky.peeker.components.screen.termsOfService.TermsOfServiceComponentImpl
import com.lyadsky.peeker.utils.ComponentFactory
import org.koin.core.component.get

fun ComponentFactory.createRootComponent(componentContext: ComponentContext): RootComponent =
    RootComponentImpl(
        componentContext = componentContext,
        componentFactory = get(),
        onboardingService = get()
    )

fun ComponentFactory.createBottomNavigationComponent(
    componentContext: ComponentContext,
    navigateToFeedbackComponent: () -> Unit,
    navigateToFaqComponent: () -> Unit,
    navigateToTermsOfServiceComponent: () -> Unit,
    navigateToPrivacyPolicyComponent: () -> Unit,
    navigateToOnboarding: () -> Unit
): BottomNavigationComponent =
    BottomNavigationComponentComponentImpl(
        componentContext = componentContext,
        componentFactory = get(),
        navigateToFeedbackComponent = navigateToFeedbackComponent,
        navigateToFaqComponent = navigateToFaqComponent,
        navigateToTermsOfServiceComponent = navigateToTermsOfServiceComponent,
        navigateToPrivacyPolicyComponent = navigateToPrivacyPolicyComponent,
        navigateToOnboarding = navigateToOnboarding
    )

fun ComponentFactory.createFeedbackComponent(
    componentContext: ComponentContext,
    onBackButtonClicked: () -> Unit
): FeedbackComponent =
    FeedbackComponentImpl(
        componentContext = componentContext,
        onBackButtonClicked = onBackButtonClicked
    )

fun ComponentFactory.createFaqComponent(
    componentContext: ComponentContext,
    onBackButtonClicked: () -> Unit
): FaqComponent =
    FaqComponentImpl(
        componentContext = componentContext,
        onBackButtonClicked = onBackButtonClicked
    )

fun ComponentFactory.createTermsOfServiceComponent(
    componentContext: ComponentContext,
    onBackButtonClicked: () -> Unit
): TermsOfServiceComponent =
    TermsOfServiceComponentImpl(
        componentContext = componentContext,
        onBackButtonClicked = onBackButtonClicked
    )

fun ComponentFactory.createPrivacyPolicyComponent(
    componentContext: ComponentContext,
    onBackButtonClicked: () -> Unit
): PrivacyPolicyComponent =
    PrivacyPolicyComponentImpl(
        componentContext = componentContext,
        onBackButtonClicked = onBackButtonClicked
    )

fun ComponentFactory.createHomeComponent(
    componentContext: ComponentContext,
    navigateToOnboarding: () -> Unit
): HomeComponent =
    HomeComponentImpl(
        componentContext = componentContext,
        componentFactory = get(),
        homePaging = get(),
        onboardingService = get(),
        navigateToOnBoarding = navigateToOnboarding
    )

fun ComponentFactory.createChatComponent(componentContext: ComponentContext): ChatComponent =
    ChatComponentImpl(componentContext)

fun ComponentFactory.createSettingsComponent(
    componentContext: ComponentContext,
    navigateToFeedbackComponent: () -> Unit,
    navigateToFaqComponent: () -> Unit,
    navigateToTermsOfServiceComponent: () -> Unit,
    navigateToPrivacyPolicyComponent: () -> Unit
): SettingsComponent =
    SettingsComponentImpl(
        componentContext = componentContext,
        navigateToFeedbackComponent = navigateToFeedbackComponent,
        navigateToFaqComponent = navigateToFaqComponent,
        navigateToTermsOfServiceComponent = navigateToTermsOfServiceComponent,
        navigateToPrivacyPolicyComponent = navigateToPrivacyPolicyComponent,
    )

fun ComponentFactory.createOnboardingComponent(
    componentContext: ComponentContext,
    navigateToHome: () -> Unit,
): OnboardingComponent =
    OnboardingComponentImpl(
        componentContext = componentContext,
        navigateToHome = navigateToHome,
        onboardingService = get()
    )
