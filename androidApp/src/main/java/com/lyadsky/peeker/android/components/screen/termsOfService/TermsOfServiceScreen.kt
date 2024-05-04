package com.lyadsky.peeker.android.components.screen.termsOfService

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.views.topBar.CommonTopBar
import com.lyadsky.peeker.components.screen.termsOfService.TermsOfServiceComponent

@Composable
fun TermsOfServiceScreen(component: TermsOfServiceComponent) {

    Column(Modifier.fillMaxSize()) {
        CommonTopBar(title = stringResource(id = R.string.terms_of_service_top_bar)){
            component.onBackButtonClick()
        }
    }
}