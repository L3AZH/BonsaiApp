package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.AppBarBackButton
import com.l3azh.bonsaiapp.Component.AppBarSaveButton
import com.l3azh.bonsaiapp.Component.BonsaiTextField
import com.l3azh.bonsaiapp.Dialog.ChoosePickOrCaptureImageDialog
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Dialog.LoadingDialog
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ViewModel.InfoAccountViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@Composable
fun InfoAccountScreen(
    infoAccountViewModel: InfoAccountViewModel,
    navHostController: NavHostController
) {
    /*var onOpenChangePassword = remember {
        mutableStateOf(false)
    }*/
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(1f),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppBarBackButton(onClick = {
                    navHostController.popBackStack()
                    infoAccountViewModel.resetState()
                })
                AppBarSaveButton(onClick = {
                    infoAccountViewModel.updateInfoAccount(context)
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
                        if (infoAccountViewModel.state.value.avatar.value == null) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_bs_camera),
                                contentDescription = "Choose Image",
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(15.dp)
                                    .clickable {
                                        infoAccountViewModel.state.value.openDialogPickAndCaptureImage()
                                    }
                            )
                        } else {
                            Image(
                                bitmap = infoAccountViewModel.state.value.avatar.value!!.asImageBitmap(),
                                contentDescription = "Avatar User",
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(15.dp)
                                    .clickable {
                                        infoAccountViewModel.state.value.openDialogPickAndCaptureImage()
                                    }
                            )
                        }
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
                        text = infoAccountViewModel.state.value.email.value,
                        style = MaterialTheme.typography.caption.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                /* Row(
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
                 }*/
                BonsaiTextField(
                    value = infoAccountViewModel.state.value.firstName.value,
                    onValueChange = { infoAccountViewModel.state.value.firstName.value = it },
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
                    value = infoAccountViewModel.state.value.lastName.value,
                    onValueChange = { infoAccountViewModel.state.value.lastName.value = it },
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
                    value = infoAccountViewModel.state.value.phoneNumber.value,
                    onValueChange = { infoAccountViewModel.state.value.phoneNumber.value = it },
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
        LoadingDialog(show = infoAccountViewModel.state.value.isLoading.value)
        if (infoAccountViewModel.state.value.onError.value) {
            InformDialog(
                show = true,
                title = "Error",
                message = infoAccountViewModel.state.value.errorMessage.value,
                positiveButtonEnable = true,
                namePositiveButton = "OK",
                onPositiveClick = { dialogState ->
                    dialogState.value = false
                    infoAccountViewModel.state.value.onError.value = false
                },
                onTapOutSideDialog = { dialogState ->
                    dialogState.value = false
                    infoAccountViewModel.state.value.onError.value = false
                }
            )
        }
        if (infoAccountViewModel.state.value.isUpdateSuccess.value) {
            InformDialog(
                show = true,
                title = "Success",
                message = "Add new Tree successful !",
                positiveButtonEnable = true,
                namePositiveButton = "OK",
                onPositiveClick = { dialogState ->
                    dialogState.value = false
                },
            )
        }
    }
    if (infoAccountViewModel.state.value.onPickAndCaptureImage.value) {
        ChoosePickOrCaptureImageDialog(
            isShow = true,
            onClose = { infoAccountViewModel.state.value.closeDialogPickAndCaptureImage() },
            nameScreen = navHostController.currentBackStackEntry!!.destination.route!!,
            onBitmapGotFromChooseOrCapture = { bitmap ->
                infoAccountViewModel.state.value.updateStateImageChoose(bitmap)
            }
        )
    }
    LaunchedEffect(key1 = true) {
        infoAccountViewModel.getInfoAccount(context)
    }
    /*if (onOpenChangePassword.value) {
        ChangePasswordDialog(
            isShow = true,
            oldPassword = "",
            onClose = { onOpenChangePassword.value = false },
            onUpdate = { newPassword ->

            })
    }*/
}

@Composable
@Preview
fun PreviewInfoAccountScreen() {
    BonsaiAppTheme {
        //InfoAccountScreen()
    }
}

