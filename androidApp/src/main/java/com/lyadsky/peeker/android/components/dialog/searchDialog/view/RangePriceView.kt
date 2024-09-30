package com.lyadsky.peeker.android.components.dialog.searchDialog.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.headerBold
import com.lyadsky.peeker.android.ui.theme.textField
import com.lyadsky.peeker.android.ui.views.textField.CommonTextField

@Composable
fun RangePriceView(
    modifier: Modifier = Modifier,
    rangeFromTextInput: String,
    rangeFromTextFieldValueChanged: (String) -> Unit,
    rangeToTextInput: String,
    rangeToTextFieldValueChanged: (String) -> Unit,
) {
    Column(modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.set_range_price),
            style = headerBold,
            color = Color.Base.black
        )

        Row(
            Modifier.padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CommonTextField(
                Modifier.weight(1f),
                textInput = rangeFromTextInput,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.set_range_price_from),
                        style = textField,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(onNext = {
                    Log.e("TAGTAG", "ok")
                }),
            ) {
                rangeFromTextFieldValueChanged(it)
            }

            CommonTextField(
                Modifier.weight(1f),
                textInput = rangeToTextInput,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.set_range_price_to),
                        style = textField,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(onDone = {
                    Log.e("TAGTAG", "ok")
                }),
            ) {
                rangeToTextFieldValueChanged(it)
            }
        }
    }
}
