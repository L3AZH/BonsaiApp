package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.AppBarBackButton
import com.l3azh.bonsaiapp.Component.AppBarSaveButton
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Dialog.LoadingDialog
import com.l3azh.bonsaiapp.ViewModel.AdminAddTreeTypeViewModel
import com.l3azh.bonsaiapp.ViewModel.AdminTreeTypeViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@Composable
fun AdminAddTreeTypeScreen(
    adminAddTreeTypeViewModel: AdminAddTreeTypeViewModel,
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
                    adminAddTreeTypeViewModel.resetState()
                    navHostController.popBackStack()
                })
                AppBarSaveButton(onClick = {
                    adminAddTreeTypeViewModel.saveNewTreeType(context)
                })
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(1f)) {
            Column(modifier = Modifier
                .fillMaxSize(1f)
                .padding(32.dp)) {
                Text(text = "Name Type", style = MaterialTheme.typography.caption.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ))
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = adminAddTreeTypeViewModel.state.value.nameType.value,
                    onValueChange = { adminAddTreeTypeViewModel.state.value.nameType.value = it},
                    modifier = Modifier.fillMaxWidth(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = false,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Description",style = MaterialTheme.typography.caption.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ))
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = adminAddTreeTypeViewModel.state.value.description.value,
                    onValueChange = {adminAddTreeTypeViewModel.state.value.description.value = it},
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(120.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = false,
                    maxLines = 4,
                )
            }
            LoadingDialog(show = adminAddTreeTypeViewModel.state.value.isLoading.value)
            if (adminAddTreeTypeViewModel.state.value.onError.value) {
                InformDialog(
                    show = true,
                    title = "Error",
                    message = adminAddTreeTypeViewModel.state.value.errorMessage.value,
                    positiveButtonEnable = true,
                    namePositiveButton = "OK",
                    onPositiveClick = { dialogState ->
                        dialogState.value = false
                        adminAddTreeTypeViewModel.state.value.onError.value = false
                    },
                    onTapOutSideDialog = { dialogState ->
                        dialogState.value = false
                        adminAddTreeTypeViewModel.state.value.onError.value = false
                    }
                )
            }
            if(adminAddTreeTypeViewModel.state.value.onAddSuccess.value){
                InformDialog(
                    show = true,
                    title = "Success",
                    message = "Add new Tree Type success !",
                    positiveButtonEnable = true,
                    namePositiveButton = "OK",
                    onPositiveClick = { dialogState ->
                        dialogState.value = false
                        adminAddTreeTypeViewModel.resetState()
                        navHostController.popBackStack()
                    },
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewAdminAddTreeTypeScreen(){
    BonsaiAppTheme {
        //AdminAddTreeTypeScreen()
    }
}