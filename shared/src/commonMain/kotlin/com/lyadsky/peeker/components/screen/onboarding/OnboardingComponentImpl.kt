package com.lyadsky.peeker.components.screen.onboarding

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.data.service.OnboardingService
import kotlinx.coroutines.launch

class OnboardingComponentImpl(
    componentContext: ComponentContext,
    private val navigateToHome: () -> Unit,
    private val onboardingService: OnboardingService
) : OnboardingComponent, BaseComponent<Unit>(componentContext, Unit) {

    override fun onNextButtonClick() {
        scope.launch {
            onboardingService.setPassedOnboarding(true)
            navigateToHome()
        }
    }
}