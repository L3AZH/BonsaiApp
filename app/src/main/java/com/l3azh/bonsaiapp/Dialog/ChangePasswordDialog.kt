package com.l3azh.bonsaiapp.Dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.l3azh.bonsaiapp.Component.BonsaiTextField
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.*

@Composable
fun ChangePasswordDialog(
    isShow: Boolean,
    oldPassword:String,
    onClose: () -> Unit,
    onUpdate: (String) -> Unit
) {
    val currentPassword = remember {
        mutableStateOf("")
    }
    val newPassword = remember {
         mutableStateOf("")
    }
    val confirmNewPassword = remember {
        mutableStateOf("")
    }

    if (isShow) {
        Box(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(color = transparent_b_73)
                .clickable(
                    indication = null, // disable ripple effect
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { }
                )
        ) {
            Card(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .wrapContentSize(),
                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp),
                elevation = 20.dp
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            modifier = Modifier
                                .size(48.dp)
                                .padding(10.dp)
                                .clickable { onClose() }
                        )
                    }
                    BonsaiTextField(
                        value = currentPassword.value,
                        onValueChange = { currentPassword.value = it },
                        keyboardType = KeyboardType.Password,
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(start = 35.dp, top = 10.dp, end = 35.dp),
                        label = {
                            Text(
                                text = "Current Password",
                                style = MaterialTheme.typography.caption.copy(
                                    fontSize = 16.sp
                                )
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_bs_password),
                                contentDescription = "Current Password"
                            )
                        }
                    )
                    BonsaiTextField(
                        value = newPassword.value,
                        onValueChange = { newPassword.value = it },
                        keyboardType = KeyboardType.Password,
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(start = 35.dp, top = 10.dp, end = 35.dp),
                        label = {
                            Text(
                                text = "New Password",
                                style = MaterialTheme.typography.caption.copy(
                                    fontSize = 16.sp
                                )
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_bs_password),
                                contentDescription = "New Password"
                            )
                        }
                    )
                    BonsaiTextField(
                        value = confirmNewPassword.value,
                        onValueChange = { confirmNewPassword.value = it},
                        keyboardType = KeyboardType.Password,
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(start = 35.dp, top = 10.dp, end = 35.dp),
                        label = {
                            Text(
                                text = "Password Confirm",
                                style = MaterialTheme.typography.caption.copy(
                                    fontSize = 16.sp
                                )
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_bs_password),
                                contentDescription = "Password Confirm"
                            )
                        }
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(vertical = 20.dp, horizontal = 32.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Green),
                            modifier = Modifier.width(100.dp)
                        ) {
                            Text(
                                text = "Save", style = MaterialTheme.typography.button.copy(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = White
                                )
                            )
                        }
                        Button(
                            onClick = {
                                onUpdate(confirmNewPassword.value)
                                      },
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = GrayLight),
                            modifier = Modifier.width(100.dp)
                        ) {
                            Text(
                                text = "Cancel", style = MaterialTheme.typography.button.copy(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = White
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewChangePasswordDialog() {
    BonsaiAppTheme {
        //ChangePasswordDialog(true, {}, {})
    }
}