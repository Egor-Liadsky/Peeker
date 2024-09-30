package com.lyadsky.peeker.android.ui.views.textField

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.textField

@SuppressLint("UnrememberedMutableInteractionSource")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatTextField(
    textInput: String,
    placeholder: String,
    keyboardActions: KeyboardActions = KeyboardActions(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChanged: (String) -> Unit,
) {
    Divider(
        Modifier
            .fillMaxWidth()
            .height(1.dp),
        color = Color.BottomBar.stroke
    )

    BasicTextField(
        value = textInput,
        onValueChange = { newText ->
            onValueChanged(newText)
        },
        enabled = enabled,
        textStyle = textField,
        keyboardActions = keyboardActions,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.ChatTextField.background),
        decorationBox = { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = textInput,
                innerTextField = innerTextField,
                placeholder = {
                    Text(
                        text = placeholder,
                        style = textField,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                enabled = true,
                singleLine = true,
                trailingIcon = {
                    Row {
                        IconButtonTextField(icon = R.drawable.ic_clip) {

                        }
                        IconButtonTextField(icon = R.drawable.ic_microphone) {

                        }
                    }
                },
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.ChatTextField.input,
                    placeholderColor = Color.ChatTextField.placeholder,
                    backgroundColor = Color.ChatTextField.background
                )
            )
        }
    )
}