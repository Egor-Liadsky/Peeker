package com.lyadsky.peeker.components.aboutApp

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.aboutApp.AboutAppComponent

class AboutAppComponentImpl(
    componentContext: ComponentContext,
    private val onBackButtonClicked: () -> Unit
): AboutAppComponent, ComponentContext by componentContext {

    override fun onBackButtonClick() {
        onBackButtonClicked()
    }
}