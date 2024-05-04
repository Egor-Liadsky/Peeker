package com.lyadsky.peeker.components.screen.settings

import com.arkivanov.decompose.value.Value

interface SettingsComponent {

    val viewStates: Value<SettingsState>

    fun onFeedbackClick()
    fun onFaqClick()
    fun onTermsOfServiceClick()
    fun onPrivacyPolicyClick()
}