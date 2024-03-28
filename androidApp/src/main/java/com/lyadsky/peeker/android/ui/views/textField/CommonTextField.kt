package com.lyadsky.peeker.android.ui.views.textField

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.textField

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onDone: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChanged: (String) -> Unit
) {

    val textInput = remember { mutableStateOf("") }

    BasicTextField(
        value = textInput.value,
        onValueChange = { newText ->
            textInput.value = newText
            onValueChanged(newText)
        },
        textStyle = textField,
        keyboardActions = KeyboardActions(onDone = { onDone?.invoke() }),
        singleLine = true,
        modifier = modifier
            .background(Color.TextField.background, shape = RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp))
            .fillMaxWidth(),
        decorationBox = { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = textInput.value,
                innerTextField = innerTextField,
                placeholder = placeholder,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                enabled = true,
                singleLine = true,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.TextField.input,
                    placeholderColor = Color.TextField.placeholder,
                    backgroundColor = Color.TextField.background
                )
            )
        }
    )
}