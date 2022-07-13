package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.l3azh.bonsaiapp.Component.AppBarBackButton
import com.l3azh.bonsaiapp.Component.AppBarSaveButton
import com.l3azh.bonsaiapp.Component.BonsaiTextField
import com.l3azh.bonsaiapp.Dialog.ChangePasswordDialog
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@Composable
fun InfoAccountScreen() {
    var onOpenChangePassword = remember {
        mutableStateOf(false)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(1f),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppBarBackButton(onClick = {
                })
                AppBarSaveButton(onClick = {

                })
            }
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(1f)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Card(shape = RoundedCornerShape(20.dp), elevation = 20.dp) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_bs_camera),
                            contentDescription = "Choose Image",
                            modifier = Modifier
                                .size(80.dp)
                                .padding(15.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 32.dp, top = 32.dp)
                ) {
                    Text(
                        text = "Email: ", style = MaterialTheme.typography.caption.copy(
                            fontSize = 16.sp,
                        )
                    )
                    Text(
                        text = "test@gmail.com", style = MaterialTheme.typography.caption.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(32.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Text(
                            text = "Password: ", style = MaterialTheme.typography.caption.copy(
                                fontSize = 16.sp,
                            )
                        )
                        Text(
                            text = "*********", style = MaterialTheme.typography.caption.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bs_edit),
                        contentDescription = "Edit password",
                        modifier = Modifier
                            .size(28.dp)
                            .clickable {
                                onOpenChangePassword.value = true
                            }
                    )
                }
                BonsaiTextField(
                    value = "",
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 35.dp, top = 10.dp, end = 35.dp),
                    label = {
                        Text(
                            text = "First Name", style = MaterialTheme.typography.caption.copy(
                                fontSize = 16.sp
                            )
                        )
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bs_note),
                            contentDescription = "First Name"
                        )
                    }
                )
                BonsaiTextField(
                    value = "",
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 35.dp, top = 10.dp, end = 35.dp),
                    label = {
                        Text(
                            text = "Last Name", style = MaterialTheme.typography.caption.copy(
                                fontSize = 16.sp
                            )
                        )
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bs_note),
                            contentDescription = "Last Name"
                        )
                    }
                )
                BonsaiTextField(
                    value = "",
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 35.dp, top = 10.dp, end = 35.dp),
                    label = {
                        Text(
                            text = "Phone Number", style = MaterialTheme.typography.caption.copy(
                                fontSize = 16.sp
                            )
                        )
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bs_note),
                            contentDescription = "Phone Number"
                        )
                    }
                )
            }
        }
    }
    if (onOpenChangePassword.value) {
        ChangePasswordDialog(
            isShow = true,
            onClose = { onOpenChangePassword.value = false },
            onUpdate = {})
    }
}

@Composable
@Preview
fun PreviewInfoAccountScreen() {
    BonsaiAppTheme {
        InfoAccountScreen()
    }
}

