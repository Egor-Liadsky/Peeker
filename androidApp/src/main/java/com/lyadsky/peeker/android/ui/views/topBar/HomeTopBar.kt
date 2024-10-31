package com.lyadsky.peeker.android.ui.views.topBar

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.textField
import com.lyadsky.peeker.android.ui.theme.titleHomeTopBar
import com.lyadsky.peeker.android.ui.views.button.SelectCityButton
import com.lyadsky.peeker.android.ui.views.textField.CommonTextField

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    searchTextInput: String,
    onSearchTextFieldClick: () -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Base.white)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.app_name_full),
                    style = titleHomeTopBar,
                    color = Color.Base.black,
                    fontSize = 14.sp
                )

                SelectCityButton {
                    Toast.makeText(context, "В разработке", Toast.LENGTH_SHORT).show()
                }
            }

            CommonTextField(
                Modifier.padding(vertical = 16.dp),
                textInput = searchTextInput,
                enabled = false,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_product_placeholder),
                        style = textField,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search"
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        Toast.makeText(
                            context,
                            "В разработке",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_qr_code_scan),
                            contentDescription = "QrCodeScanner"
                        )
                    }
                },
                onClick = {
                    onSearchTextFieldClick()
                }
            ) { }
        }
    }
}
