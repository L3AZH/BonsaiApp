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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.BonsaiTextField
import com.l3azh.bonsaiapp.Constant
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Dialog.LoadingDialog
import com.l3azh.bonsaiapp.MainActivity
import com.l3azh.bonsaiapp.Navigation.BonsaiNavigationTag
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.Util.SharePrefUtils
import com.l3azh.bonsaiapp.ViewModel.LoginViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.Green
import com.l3azh.bonsaiapp.ui.theme.GreenLight
import com.l3azh.bonsaiapp.ui.theme.White


@Composable
fun LoginScreen(
    navHostController: NavHostController? = null,
    loginViewModel: LoginViewModel,
) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(color = Color.White)
    ) {
        LoadingDialog(
            show = loginViewModel.state.value.isLoading.value
        )
        if(loginViewModel.state.value.onError.value){
            InformDialog(
                show = true,
                title = "Error",
                message = loginViewModel.state.value.errorMessage.value,
                positiveButtonEnable = true,
                namePositiveButton = "OK",
                onPositiveClick = {dialogState ->
                    dialogState.value = false
                    loginViewModel.state.value.onError.value = false
                }
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Login",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 36.dp, top = 102.dp),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Green
                    )
                )
                BonsaiTextField(
                    value = loginViewModel.state.value.email.value,
                    onValueChange = { loginViewModel.state.value.email.value = it },
                    keyboardType = KeyboardType.Text,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 35.dp, top = 88.dp, end = 35.dp),
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
                    value = loginViewModel.state.value.password.value,
                    onValueChange = { loginViewModel.state.value.password.value = it },
                    keyboardType = KeyboardType.Password,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 35.dp, top = 32.dp, end = 35.dp),
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
                Button(
                    onClick = {
                        loginViewModel.login(
                            email = loginViewModel.state.value.email.value,
                            password = loginViewModel.state.value.password.value,
                            onLoginSuccess = { token, email, role ->
                                SharePrefUtils.saveTokenToPref(context, token)
                                SharePrefUtils.saveEmailToPref(context, email)
                                SharePrefUtils.saveRoleToPref(context, role)
                                if (Constant.USER_ROLE.equals(role)){
                                    navHostController!!.navigate(BonsaiNavigationTag.UserMainMenuScreen.name){
                                        navHostController.popBackStack()
                                    }
                                } else {
                                    navHostController!!.navigate(BonsaiNavigationTag.AdminMainMenuScreen.name){
                                        navHostController.popBackStack()
                                    }
                                }
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 35.dp, top = 88.dp, end = 35.dp),
                    shape = RoundedCornerShape(30),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Green)
                ) {
                    Text(
                        text = "Login",
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
                        .padding(top = 32.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Do not have an Account? ",
                        style = MaterialTheme.typography.caption.copy(
                            fontSize = 14.sp,
                            color = GreenLight
                        )
                    )
                    Text(
                        text = "Sign Up",
                        style = MaterialTheme.typography.caption.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = GreenLight
                        ),
                        modifier = Modifier.clickable {
                            navHostController!!.navigate(BonsaiNavigationTag.RegisterScreen.name)
                        })
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewLoginScreen() {
    BonsaiAppTheme {
        val context = LocalContext.current
        LoginScreen(null, (context as MainActivity).loginViewModel)
    }
}