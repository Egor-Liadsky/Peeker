package com.lyadsky.peeker.components.screen.settings

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.BuildKonfig
import com.lyadsky.peeker.components.BaseComponent

class SettingsComponentImpl(
    componentContext: ComponentContext,
    private val navigateToFeedbackComponent: () -> Unit,
    private val navigateToFaqComponent: () -> Unit,
    private val navigateToTermsOfServiceComponent: () -> Unit,
    private val navigateToPrivacyPolicyComponent: () -> Unit
) : SettingsComponent, BaseComponent<SettingsState>(componentContext, SettingsState()) {

    init {
        viewState = viewState.copy(
            appVersion = BuildKonfig.APP_VERSION,
            linkVkGroup = BuildKonfig.VK_GROUP,
            linkTelegramGroup = BuildKonfig.TELEGRAM_GROUP,
        )
    }

    override fun onFeedbackClick() {
        navigateToFeedbackComponent()
    }

    override fun onFaqClick() {
        navigateToFaqComponent()
    }

    override fun onTermsOfServiceClick() {
        navigateToTermsOfServiceComponent()
    }

    override fun onPrivacyPolicyClick() {
        navigateToPrivacyPolicyComponent()
    }
}