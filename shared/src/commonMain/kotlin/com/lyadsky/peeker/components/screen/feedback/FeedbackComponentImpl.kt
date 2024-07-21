package com.lyadsky.peeker.components.screen.feedback

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.BuildKonfig

class FeedbackComponentImpl(
    componentContext: ComponentContext,
    private val onBackButtonClicked: () -> Unit
): FeedbackComponent, ComponentContext by componentContext {

    override fun onBackButtonClick() {
        onBackButtonClicked()
    }

    override fun getFeedbackEmail(): String = BuildKonfig.FEEDBACK_EMAIL
}