package com.lyadsky.moneychecker.components.aboutApp

import com.arkivanov.decompose.ComponentContext

class AboutAppComponentImpl(
    componentContext: ComponentContext,
    private val onBackButtonClicked: () -> Unit
): AboutAppComponent, ComponentContext by componentContext {

    override fun onBackButtonClick() {
        onBackButtonClicked()
    }
}