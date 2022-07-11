package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.l3azh.bonsaiapp.Component.AppBarSaveButton
import com.l3azh.bonsaiapp.Component.BonsaiDropDownMenu
import com.l3azh.bonsaiapp.Dialog.ChoosePickOrCaptureImageDialog
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Dialog.LoadingDialog
import com.l3azh.bonsaiapp.Model.TreeTypeState
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ViewModel.AdminAddTreeViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@Composable
fun AdminAddTreeScreen(
    adminAddTreeViewModel: AdminAddTreeViewModel,
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
                    adminAddTreeViewModel.resetState()
                    navHostController.popBackStack()
                })
                AppBarSaveButton(onClick = {
                    adminAddTreeViewModel.createNewType(context)
                })
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
                Text(
                    text = "Name Type", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = adminAddTreeViewModel.state.value.nameTree.value,
                    onValueChange = { adminAddTreeViewModel.state.value.nameTree.value = it },
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
                    adminAddTreeViewModel.state.value.listType.value,
                    adminAddTreeViewModel.state.value.typeChoose.value,
                    onSelectedItem = { itemSelected ->
                        adminAddTreeViewModel.state.value.typeChoose.value = itemSelected
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
                    value = adminAddTreeViewModel.state.value.price.value,
                    onValueChange = {
                        adminAddTreeViewModel.state.value.price.value = it
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
                    value = adminAddTreeViewModel.state.value.description.value,
                    onValueChange = { adminAddTreeViewModel.state.value.description.value = it },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(120.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = false,
                    maxLines = 4,
                )
                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Picture", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (adminAddTreeViewModel.state.value.picture.value == null) {
                    Row(modifier = Modifier.fillMaxWidth(1f)) {
                        Card(shape = RoundedCornerShape(20), elevation = 10.dp) {
                            Icon(painter = painterResource(id = R.drawable.ic_bs_camera),
                                contentDescription = "Choose Image",
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(20))
                                    .padding(10.dp)
                                    .clickable {
                                        adminAddTreeViewModel.state.value.openDialogPickAndCaptureImage()
                                    })
                        }
                    }
                } else {
                    Image(
                        bitmap = adminAddTreeViewModel.state.value.picture.value!!.asImageBitmap(),
                        contentDescription = "Picture choose",
                        modifier = Modifier.size(100.dp).clickable {
                            adminAddTreeViewModel.state.value.openDialogPickAndCaptureImage()
                        }
                    )
                }
            }
            LoadingDialog(show = adminAddTreeViewModel.state.value.isLoading.value)
            if (adminAddTreeViewModel.state.value.onError.value) {
                InformDialog(
                    show = true,
                    title = "Error",
                    message = adminAddTreeViewModel.state.value.errorMessage.value,
                    positiveButtonEnable = true,
                    namePositiveButton = "OK",
                    onPositiveClick = { dialogState ->
                        dialogState.value = false
                        adminAddTreeViewModel.state.value.onError.value = false
                    },
                    onTapOutSideDialog = { dialogState ->
                        dialogState.value = false
                        adminAddTreeViewModel.state.value.onError.value = false
                    }
                )
            }
            if(adminAddTreeViewModel.state.value.onAddSuccess.value){
                InformDialog(
                    show = true,
                    title = "Success",
                    message = "Add new Tree successful !",
                    positiveButtonEnable = true,
                    namePositiveButton = "OK",
                    onPositiveClick = { dialogState ->
                        dialogState.value = false
                        adminAddTreeViewModel.resetState()
                        navHostController.popBackStack()
                    },
                )
            }
        }
    }
    if(adminAddTreeViewModel.state.value.onPickAndCaptureImage.value){
        ChoosePickOrCaptureImageDialog(
            isShow = true,
            onClose = { adminAddTreeViewModel.state.value.closeDialogPickAndCaptureImage()},
            nameScreen = navHostController!!.currentBackStackEntry!!.destination.route!!,
            onBitmapGotFromChooseOrCapture = { bitmap ->
                adminAddTreeViewModel.state.value.updateStateImageChoose(bitmap)
            }
        )
    }
    LaunchedEffect(key1 = true) {
        adminAddTreeViewModel.getAllTreeType(context)
    }
}

@Composable
@Preview
fun PreviewAdminAddTreeScreen() {
    BonsaiAppTheme {
        // AdminAddTreeScreen()
    }
}