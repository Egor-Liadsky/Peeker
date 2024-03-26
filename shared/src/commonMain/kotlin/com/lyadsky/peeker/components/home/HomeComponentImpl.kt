package com.lyadsky.peeker.components.home

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.components.home.HomeComponent

class HomeComponentImpl(
    componentContext: ComponentContext,
    private val navigateToAboutAppComponent: () -> Unit
): HomeComponent, BaseComponent<Unit>(componentContext, Unit) {

    override fun navigateToAboutApp() {
        navigateToAboutAppComponent()
    }
}