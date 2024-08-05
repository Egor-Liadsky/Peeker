package com.lyadsky.peeker.android.components.screen.termsOfService

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
import com.lyadsky.peeker.components.screen.termsOfService.TermsOfServiceComponent

@Composable
fun TermsOfServiceScreen(component: TermsOfServiceComponent) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Base.white)
    ) {
        CommonTopBar(title = stringResource(id = R.string.terms_of_service_top_bar)) {
            component.onBackButtonClick()
        }

        val termsOfServiceList = listOf(
            Pair(
                stringResource(id = R.string.terms_of_service_general_provisions),
                stringResource(id = R.string.terms_of_service_general_provisions_description)
            ),
            Pair(
                stringResource(id = R.string.terms_of_service_registration),
                stringResource(id = R.string.terms_of_service_registration_description)
            ),
            Pair(
                stringResource(id = R.string.terms_of_service_rights_and_obligations_of_parties),
                stringResource(id = R.string.terms_of_service_rights_and_obligations_of_parties_description)
            ),
            Pair(
                stringResource(id = R.string.terms_of_service_pay_and_cashback),
                stringResource(id = R.string.terms_of_service_pay_and_cashback_description)
            ),
            Pair(
                stringResource(id = R.string.terms_of_service_intellectual_property),
                stringResource(id = R.string.terms_of_service_intellectual_property_description)
            ),
            Pair(
                stringResource(id = R.string.terms_of_service_responsibility),
                stringResource(id = R.string.terms_of_service_responsibility_description)
            ),
            Pair(
                stringResource(id = R.string.terms_of_service_change_terms),
                stringResource(id = R.string.terms_of_service_change_terms_description)
            ),
            Pair(
                stringResource(id = R.string.terms_of_service_final_provisions),
                stringResource(id = R.string.terms_of_service_final_provisions_description)
            ),
        )

        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = termsOfServiceList) { termsOfServiceList ->
                AdditionalButton(data = termsOfServiceList)
            }
        }
    }
}