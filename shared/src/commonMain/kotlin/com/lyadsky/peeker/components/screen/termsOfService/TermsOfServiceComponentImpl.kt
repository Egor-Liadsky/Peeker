package com.lyadsky.peeker.components.screen.termsOfService

import com.arkivanov.decompose.ComponentContext

class TermsOfServiceComponentImpl(
    componentContext: ComponentContext,
    private val onBackButtonClicked: () -> Unit
): TermsOfServiceComponent, ComponentContext by componentContext {

    override fun onBackButtonClick() {
        onBackButtonClicked()
    }
}