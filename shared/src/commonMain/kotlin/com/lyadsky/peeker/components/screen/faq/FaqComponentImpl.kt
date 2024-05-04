package com.lyadsky.peeker.components.screen.faq

import com.arkivanov.decompose.ComponentContext

class FaqComponentImpl(
    componentContext: ComponentContext,
    private val onBackButtonClicked: () -> Unit
): FaqComponent, ComponentContext by componentContext {

    override fun onBackButtonClick() {
        onBackButtonClicked()
    }
}