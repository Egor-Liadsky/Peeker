package com.lyadsky.peeker.components.screen.feedback

import com.arkivanov.decompose.ComponentContext

class FeedbackComponentImpl(
    componentContext: ComponentContext,
    private val onBackButtonClicked: () -> Unit
): FeedbackComponent, ComponentContext by componentContext {

    override fun onBackButtonClick() {
        onBackButtonClicked()
    }
}