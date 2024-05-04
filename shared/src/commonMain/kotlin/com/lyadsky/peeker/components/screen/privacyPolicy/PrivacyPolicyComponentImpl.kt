package com.lyadsky.peeker.components.screen.privacyPolicy

import com.arkivanov.decompose.ComponentContext

class PrivacyPolicyComponentImpl(
    componentContext: ComponentContext,
    private val onBackButtonClicked: () -> Unit
): PrivacyPolicyComponent, ComponentContext by componentContext {

    override fun onBackButtonClick() {
        onBackButtonClicked()
    }
}