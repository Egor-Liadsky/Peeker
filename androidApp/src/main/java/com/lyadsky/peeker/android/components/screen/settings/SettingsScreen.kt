package com.lyadsky.peeker.android.components.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.components.screen.settings.layouts.AboutAppView
import com.lyadsky.peeker.android.components.screen.settings.layouts.SettingItemView
import com.lyadsky.peeker.android.models.SettingItem
import com.lyadsky.peeker.android.ui.views.topBar.CommonTopBar
import com.lyadsky.peeker.components.screen.settings.SettingsComponent

@Composable
fun SettingsScreen(component: SettingsComponent) {

    val state = component.viewStates.subscribeAsState()

    Column(Modifier.fillMaxSize()) {

        CommonTopBar(title = stringResource(id = R.string.settings_top_bar))

        val settingItems = listOf(
            SettingItem(
                title = stringResource(id = R.string.feedback),
                icon = R.drawable.ic_feedback,
                onClick = { component.onFeedbackClick() }
            ),
            SettingItem(
                title = stringResource(id = R.string.faq),
                icon = R.drawable.ic_faq,
                onClick = { component.onFaqClick() }
            ),
            SettingItem(
                title = stringResource(id = R.string.terms_of_service),
                icon = R.drawable.ic_terms_of_service,
                onClick = { component.onTermsOfServiceClick() }
            ),
            SettingItem(
                title = stringResource(id = R.string.privacy_policy),
                icon = R.drawable.ic_privacy_policy,
                onClick = { component.onPrivacyPolicyClick() }
            ),
        )

        LazyColumn(
            modifier = Modifier.padding(vertical = 30.dp)
        ) {

            items(items = settingItems) { item ->
                SettingItemView(settingItem = item)
            }

            item {
                Spacer(modifier = Modifier.height(70.dp))
            }

            item {
                AboutAppView(
                    appVersion = state.value.appVersion.toString(),
                    vkLink = state.value.linkVkGroup.toString(),
                    telegramLink = state.value.linkTelegramGroup.toString()
                )
            }
        }
    }
}