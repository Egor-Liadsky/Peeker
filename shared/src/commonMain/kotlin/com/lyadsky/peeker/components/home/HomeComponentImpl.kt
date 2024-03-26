package com.lyadsky.moneychecker.components.home

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.moneychecker.components.home.HomeComponent

class HomeComponentImpl(
    componentContext: ComponentContext,
    private val navigateToAboutAppComponent: () -> Unit
): HomeComponent, ComponentContext by componentContext {

    override fun navigateToAboutApp() {
        navigateToAboutAppComponent()
    }
}