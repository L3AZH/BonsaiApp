package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.BonsaiTextField
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Dialog.LoadingDialog
import com.l3azh.bonsaiapp.MainActivity
import com.l3azh.bonsaiapp.Navigation.BonsaiNavigationTag
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ViewModel.RegisterViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.Green
import com.l3azh.bonsaiapp.ui.theme.GreenLight
import com.l3azh.bonsaiapp.ui.theme.White

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel,
    navHostController: NavHostController? = null,
) {
    Box(modifier = Modifier.fillMaxSize(1f)) {
        LoadingDialog(show = registerViewModel.state.value.isLoading.value)
        if (registerViewModel.state.value.onError.value) {
            InformDialog(
                show = true,
                title = "Error",
                message = registerViewModel.state.value.errorMessage.value,
                positiveButtonEnable = true,
                namePositiveButton = "OK",
                onPositiveClick = { dialogState ->
                    dialogState.value = false
                    registerViewModel.state.value.onError.value = false
                },
                onTapOutSideDialog = { dialogState ->
                    dialogState.value = false
                    registerViewModel.state.value.onError.value = false
                }
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(color = White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Sign Up",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 36.dp, top = 18.dp),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Green
                    )
                )
                BonsaiTextField(
                    value = registerViewModel.state.value.email.value,
                    onValueChange = { registerViewModel.state.value.email.value = it },
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
                    value = registerViewModel.state.value.firstName.value,
                    onValueChange = { registerViewModel.state.value.firstName.value = it },
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
                    value = registerViewModel.state.value.lastName.value,
                    onValueChange = { registerViewModel.state.value.lastName.value = it },
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
                    value = registerViewModel.state.value.phoneNumber.value,
                    onValueChange = { registerViewModel.state.value.phoneNumber.value = it },
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
                    value = registerViewModel.state.value.password.value,
                    onValueChange = { registerViewModel.state.value.password.value = it },
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
                    value = registerViewModel.state.value.confirmPassword.value,
                    onValueChange = { registerViewModel.state.value.confirmPassword.value = it },
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
                Button(
                    onClick = {
                        registerViewModel.registerUser(onSignUpSuccess = {

                        })
                    },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 35.dp, top = 18.dp, end = 35.dp),
                    shape = RoundedCornerShape(30),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Green)
                ) {
                    Text(
                        text = "Create",
                        style = MaterialTheme.typography.button.copy(
                            fontSize = 16.sp,
                            color = White
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Already have an Account? ",
                        style = MaterialTheme.typography.caption.copy(
                            fontSize = 14.sp,
                            color = GreenLight
                        )
                    )
                    Text(
                        text = "Sign In",
                        style = MaterialTheme.typography.caption.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = GreenLight
                        ),
                        modifier = Modifier.clickable {
                            navHostController!!.navigate(BonsaiNavigationTag.LoginScreen.name)
                        })
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewRegisterScreen() {
    BonsaiAppTheme {
        val context = LocalContext.current
        RegisterScreen((context as MainActivity).registerViewModel)
    }
}