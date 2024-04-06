package com.lyadsky.peeker.components.home

import com.arkivanov.decompose.value.Value

interface HomeComponent {

    val viewStates: Value<HomeState>

    fun navigateToAboutApp()
}