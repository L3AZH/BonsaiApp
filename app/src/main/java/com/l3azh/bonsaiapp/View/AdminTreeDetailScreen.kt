package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.AppBarBackButton
import com.l3azh.bonsaiapp.Component.AppBarDeleteButton
import com.l3azh.bonsaiapp.Component.AppBarSaveButton
import com.l3azh.bonsaiapp.Component.BonsaiDropDownMenu
import com.l3azh.bonsaiapp.Dialog.ChoosePickOrCaptureImageDialog
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Dialog.LoadingDialog
import com.l3azh.bonsaiapp.Model.TreeTypeState
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ViewModel.AdminTreeDetailViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@Composable
fun AdminTreeDetailScreen(
    uuidTree:String,
    adminTreeDetailViewModel: AdminTreeDetailViewModel,
    navHostController: NavHostController
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppBarBackButton(onClick = {
                    adminTreeDetailViewModel.resetState()
                    navHostController.popBackStack()
                })
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppBarDeleteButton {}
                    AppBarSaveButton {
                        adminTreeDetailViewModel.updateTreeInfo(context, uuidTree)
                    }
                }
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(1f)) {
            Column(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(32.dp)
            ) {
                Column {
                    Text(
                        text = "UUID: ", style = MaterialTheme.typography.caption.copy(
                            fontSize = 16.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = adminTreeDetailViewModel.state.value.uuidTree.value,
                        style = MaterialTheme.typography.caption.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Picture", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (adminTreeDetailViewModel.state.value.picture.value == null) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bs_tree),
                            contentDescription = "Picture choose", tint = Color.Black,
                            modifier = Modifier
                                .size(100.dp)
                                .clickable {
                                    adminTreeDetailViewModel.state.value.openDialogPickAndCaptureImage()
                                }
                        )
                    } else {
                        Image(bitmap = adminTreeDetailViewModel.state.value.picture.value!!.asImageBitmap(),
                            contentDescription = "Picture choose",
                            modifier = Modifier
                                .size(100.dp)
                                .clickable {
                                    adminTreeDetailViewModel.state.value.openDialogPickAndCaptureImage()
                                })
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Name Type", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = adminTreeDetailViewModel.state.value.nameTree.value,
                    onValueChange = { adminTreeDetailViewModel.state.value.nameTree.value = it },
                    modifier = Modifier.fillMaxWidth(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = false,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Type", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                BonsaiDropDownMenu<TreeTypeState>(
                    Modifier.fillMaxWidth(1f),
                    adminTreeDetailViewModel.state.value.listType.value,
                    adminTreeDetailViewModel.state.value.typeChoose.value,
                    onSelectedItem = { treeTypeState ->
                        adminTreeDetailViewModel.state.value.typeChoose.value = treeTypeState
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Price", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = adminTreeDetailViewModel.state.value.price.value,
                    onValueChange = {
                        adminTreeDetailViewModel.state.value.price.value = it
                    },
                    modifier = Modifier.fillMaxWidth(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = false,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Description", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = adminTreeDetailViewModel.state.value.description.value,
                    onValueChange = { adminTreeDetailViewModel.state.value.description.value = it },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(120.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = false,
                    maxLines = 4,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        LoadingDialog(show = adminTreeDetailViewModel.state.value.isLoading.value)
        if (adminTreeDetailViewModel.state.value.onError.value) {
            InformDialog(
                show = true,
                title = "Error",
                message = adminTreeDetailViewModel.state.value.errorMessage.value,
                positiveButtonEnable = true,
                namePositiveButton = "OK",
                onPositiveClick = { dialogState ->
                    dialogState.value = false
                    adminTreeDetailViewModel.state.value.onError.value = false
                },
                onTapOutSideDialog = { dialogState ->
                    dialogState.value = false
                    adminTreeDetailViewModel.state.value.onError.value = false
                }
            )
        }
        if (adminTreeDetailViewModel.state.value.onUdpateSuccess.value) {
            InformDialog(
                show = true,
                title = "Success",
                message = "Update successful !",
                positiveButtonEnable = true,
                namePositiveButton = "OK",
                onPositiveClick = { dialogState ->
                    dialogState.value = false
                    adminTreeDetailViewModel.state.value.onUdpateSuccess.value = false
                },
                onTapOutSideDialog = { dialogState ->
                    dialogState.value = false
                    adminTreeDetailViewModel.state.value.onUdpateSuccess.value = false
                }
            )
        }
    }
    if (adminTreeDetailViewModel.state.value.onPickAndCaptureImage.value) {
        ChoosePickOrCaptureImageDialog(
            isShow = true,
            onClose = { adminTreeDetailViewModel.state.value.closeDialogPickAndCaptureImage() },
            nameScreen = navHostController.currentBackStackEntry!!.destination.route!!,
            onBitmapGotFromChooseOrCapture = { bitmap ->
                adminTreeDetailViewModel.state.value.updateStateImageChoose(bitmap)
            }
        )
    }
    LaunchedEffect(key1 = true){
        adminTreeDetailViewModel.getTreeInfo(context, uuidTree)
    }
}

@Composable
@Preview
fun PreviewAdminTreeDetailScreen() {
    BonsaiAppTheme {
        //AdminTreeDetailScreen()
    }
}