package com.l3azh.bonsaiapp.Component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@Composable
fun BonsaiTextField(
    value: String,
    onValueChange: (value: String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier,
    label: @Composable (() -> Unit)?,
    icon: @Composable (() -> Unit)?
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = icon,
        label = label,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

@Preview
@Composable
fun PreviewBonsaiTextField() {
    BonsaiAppTheme() {
        BonsaiTextField(value = "Test", onValueChange = {}, modifier = Modifier, label = null) {

            Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Test")
        }
    }
}
