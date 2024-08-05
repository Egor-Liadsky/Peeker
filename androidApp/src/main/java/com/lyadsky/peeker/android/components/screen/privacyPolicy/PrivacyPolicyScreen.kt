package com.lyadsky.peeker.android.components.screen.privacyPolicy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.views.button.AdditionalButton
import com.lyadsky.peeker.android.ui.views.topBar.CommonTopBar
import com.lyadsky.peeker.components.screen.privacyPolicy.PrivacyPolicyComponent

@Composable
fun PrivacyPolicyScreen(component: PrivacyPolicyComponent) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Base.white)
    ) {
        CommonTopBar(title = stringResource(id = R.string.privacy_policy_top_bar)) {
            component.onBackButtonClick()
        }

        val privacyPolicyList = listOf(
            Pair(
                stringResource(id = R.string.privacy_policy_general_provisions),
                stringResource(id = R.string.privacy_policy_general_provisions_description)
            ),
            Pair(
                stringResource(id = R.string.privacy_policy_basic_concepts_used_policy),
                stringResource(id = R.string.privacy_policy_basic_concepts_used_policy_description)
            ),
            Pair(
                stringResource(id = R.string.privacy_policy_basic_rights_and_obligations_operator),
                stringResource(id = R.string.privacy_policy_basic_rights_and_obligations_operator_description)
            ),
            Pair(
                stringResource(id = R.string.privacy_policy_basic_rights_and_obligations_of_personal_data_subjects),
                stringResource(id = R.string.privacy_policy_basic_rights_and_obligations_of_personal_data_subjects_description)
            ),
            Pair(
                stringResource(id = R.string.privacy_policy_principles_of_personal_data_processing),
                stringResource(id = R.string.privacy_policy_principles_of_personal_data_processing_description)
            ),
            Pair(
                stringResource(id = R.string.privacy_policy_personal_data_processing_goals),
                stringResource(id = R.string.privacy_policy_personal_data_processing_goals_description)
            ),
        )

        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = privacyPolicyList) { privacyPolicy ->
                AdditionalButton(data = privacyPolicy)
            }
        }
    }
}