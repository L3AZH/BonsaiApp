package com.l3azh.bonsaiapp.Dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.defaultDialogButton
import com.l3azh.bonsaiapp.ui.theme.defaultDivider
import com.l3azh.bonsaiapp.ui.theme.transparent90

@Composable
fun InformDialog(
    show: Boolean,
    title: String,
    message: String,
    positiveButtonEnable: Boolean = false,
    namePositiveButton: String = "OK",
    negativeButtonEnable: Boolean = false,
    nameNegativeButton: String = "Cancel",
    onPositiveClick: (MutableState<Boolean>) -> Unit = {},
    onNegativeClick: (MutableState<Boolean>) -> Unit = {},
) {
    val openDialog = rememberSaveable {
        mutableStateOf(show)
    }
    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            Card(
                shape = RoundedCornerShape(10),
                backgroundColor = transparent90
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = title, style = MaterialTheme.typography.caption.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        ), modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = message, style = MaterialTheme.typography.body1.copy(
                            fontSize = 14.sp
                        ), modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                    if (positiveButtonEnable || negativeButtonEnable) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .height(1.dp)
                                .background(color = defaultDivider)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(1f)
                        ) {
                            if (positiveButtonEnable) {
                                TextButton(
                                    onClick = { onPositiveClick(openDialog) },
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .weight(1f)
                                ) {
                                    Text(
                                        text = namePositiveButton,
                                        style = MaterialTheme.typography.button.copy(
                                            fontSize = 16.sp,
                                            color = defaultDialogButton
                                        )
                                    )
                                }
                            }
                            if (positiveButtonEnable && negativeButtonEnable) {
                                Divider(
                                    modifier = Modifier
                                        .height(48.dp)
                                        .width(1.dp)
                                        .background(color = defaultDivider)
                                )
                            }
                            if (negativeButtonEnable) {
                                TextButton(
                                    onClick = { onNegativeClick(openDialog) },
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .weight(1f)
                                ) {
                                    Text(
                                        text = nameNegativeButton,
                                        style = MaterialTheme.typography.button.copy(
                                            fontSize = 16.sp,
                                            color = defaultDialogButton
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewInformDialog() {
    BonsaiAppTheme {
        InformDialog(
            show = true, title = "test", message = "ok",
            positiveButtonEnable = true, namePositiveButton = "test",
            negativeButtonEnable = true, nameNegativeButton = "test2"
        )
    }
}