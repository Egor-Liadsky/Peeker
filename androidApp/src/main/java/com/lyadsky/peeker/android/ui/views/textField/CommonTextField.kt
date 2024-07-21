package com.lyadsky.peeker.android.ui.views.textField

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.textField

@SuppressLint("UnrememberedMutableInteractionSource")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    textInput: String,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onClick: (() -> Unit)? = null,
    onBackButtonClick: (() -> Unit)? = null,
    onValueChanged: (String) -> Unit,
) {

    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (onBackButtonClick != null) {
            IconButton(
                onClick = { onBackButtonClick() },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "ic back",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        BasicTextField(
            value = textInput,
            onValueChange = { newText ->
                onValueChanged(newText)
            },
            enabled = enabled,
            textStyle = textField,
            keyboardActions = keyboardActions,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(Color.TextField.background)
                .then(
                    if (onClick != null) Modifier.clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple()
                    ) {
                        onClick()
                    }
                    else Modifier
                ),
            decorationBox = { innerTextField ->
                TextFieldDefaults.TextFieldDecorationBox(
                    value = textInput,
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
}