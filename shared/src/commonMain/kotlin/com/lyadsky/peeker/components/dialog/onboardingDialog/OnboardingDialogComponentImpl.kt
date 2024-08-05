package com.lyadsky.peeker.components.dialog.onboardingDialog

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.data.service.OnboardingService
import kotlinx.coroutines.launch

class OnboardingDialogComponentImpl(
    componentContext: ComponentContext,
    private val onboardingService: OnboardingService,
    private val onDismissed: () -> Unit
) : OnboardingDialogComponent, BaseComponent<Unit>(componentContext, Unit) {

    override fun onDismissClick() {
        scope.launch {
            onboardingService.setPassedOnboarding(true)
            onDismissed()
        }
    }
}