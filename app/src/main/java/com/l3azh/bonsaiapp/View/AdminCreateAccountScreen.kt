package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.AppBarBackButton
import com.l3azh.bonsaiapp.Component.AppBarSaveButton
import com.l3azh.bonsaiapp.Component.BonsaiTextField
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Dialog.LoadingDialog
import com.l3azh.bonsaiapp.ViewModel.AdminCreateAccountViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.Green

@Composable
fun AdminCreateAccountScreen(
    adminCreateAccountViewModel: AdminCreateAccountViewModel,
    navHostController: NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(1f),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppBarBackButton(onClick = {
                    adminCreateAccountViewModel.resetState()
                    navHostController.popBackStack()
                })
                Text(
                    text = "Add Account",
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Green
                    ),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start
                )
                AppBarSaveButton(onClick = {
                    adminCreateAccountViewModel.registerUser()
                })
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(1f)) {
            Column(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                BonsaiTextField(
                    value = adminCreateAccountViewModel.state.value.email.value,
                    onValueChange = { adminCreateAccountViewModel.state.value.email.value = it },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 35.dp, top = 10.dp, end = 35.dp),
                    label = {
                        Text(
                            text = "Email", style = MaterialTheme.typography.caption.copy(
                                fontSize = 16.sp
                            )
                        )
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bs_email),
                            contentDescription = "Email"
                        )
                    }
                )
                BonsaiTextField(
                    value = adminCreateAccountViewModel.state.value.firstName.value,
                    onValueChange = { adminCreateAccountViewModel.state.value.firstName.value = it },
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
                    value = adminCreateAccountViewModel.state.value.lastName.value,
                    onValueChange = { adminCreateAccountViewModel.state.value.lastName.value = it },
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
                    value = adminCreateAccountViewModel.state.value.phoneNumber.value,
                    onValueChange = { adminCreateAccountViewModel.state.value.phoneNumber.value = it },
                    keyboardType = KeyboardType.Number,
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
                BonsaiTextField(
                    value = adminCreateAccountViewModel.state.value.password.value,
                    onValueChange = { adminCreateAccountViewModel.state.value.password.value = it },
                    keyboardType = KeyboardType.Password,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 35.dp, top = 10.dp, end = 35.dp),
                    label = {
                        Text(
                            text = "Password", style = MaterialTheme.typography.caption.copy(
                                fontSize = 16.sp
                            )
                        )
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bs_password),
                            contentDescription = "Password"
                        )
                    }
                )
                BonsaiTextField(
                    value = adminCreateAccountViewModel.state.value.confirmPassword.value,
                    onValueChange = { adminCreateAccountViewModel.state.value.confirmPassword.value = it },
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
            }
        }
    }
    LoadingDialog(show = adminCreateAccountViewModel.state.value.isLoading.value)
    if (adminCreateAccountViewModel.state.value.onError.value) {
        InformDialog(
            show = true,
            title = "Error",
            message = adminCreateAccountViewModel.state.value.errorMessage.value,
            positiveButtonEnable = true,
            namePositiveButton = "OK",
            onPositiveClick = { dialogState ->
                dialogState.value = false
                adminCreateAccountViewModel.state.value.onError.value = false
            },
            onTapOutSideDialog = { dialogState ->
                dialogState.value = false
                adminCreateAccountViewModel.state.value.onError.value = false
            }
        )
    }
    if(adminCreateAccountViewModel.state.value.onCreateAccountSuccess.value){
        InformDialog(
            show = true,
            title = "Success",
            message = "Add new Account Role Admin Successful !",
            positiveButtonEnable = true,
            namePositiveButton = "OK",
            onPositiveClick = { dialogState ->
                dialogState.value = false
                adminCreateAccountViewModel.resetState()
                navHostController.popBackStack()
            }
        )
    }
}

@Composable
@Preview
fun PreviewAdminCreateAccountScreen(){
    BonsaiAppTheme {
        //AdminCreateAccountScreen()
    }
}