package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import com.l3azh.bonsaiapp.Component.AppBarDeleteButton
import com.l3azh.bonsaiapp.Component.AppBarSaveButton
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Dialog.LoadingDialog
import com.l3azh.bonsaiapp.ViewModel.AdminTreeTypeDetailViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@Composable
fun AdminTreeTypeDetailScreen(
    adminTreeTypeDetailViewModel: AdminTreeTypeDetailViewModel,
    navHostController: NavHostController
) {
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
                    adminTreeTypeDetailViewModel.resetSate()
                    navHostController.popBackStack()
                })
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppBarDeleteButton {
                        adminTreeTypeDetailViewModel.deleteTree(
                            context,
                            adminTreeTypeDetailViewModel.state.value.uuidTreeType.value
                        )
                    }
                    AppBarSaveButton(onClick = {
                        adminTreeTypeDetailViewModel.updateTreeType(context)
                    })
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
                Row {
                    Text(
                        text = "UUID: ", style = MaterialTheme.typography.caption.copy(
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = adminTreeTypeDetailViewModel.state.value.uuidTreeType.value,
                        style = MaterialTheme.typography.caption.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
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
                    value = adminTreeTypeDetailViewModel.state.value.name.value,
                    onValueChange = { adminTreeTypeDetailViewModel.state.value.name.value = it },
                    modifier = Modifier.fillMaxWidth(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
                    value = adminTreeTypeDetailViewModel.state.value.description.value,
                    onValueChange = {
                        adminTreeTypeDetailViewModel.state.value.description.value = it
                    },
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
        LoadingDialog(
            show = adminTreeTypeDetailViewModel.state.value.isLoading.value
        )
        if (adminTreeTypeDetailViewModel.state.value.onError.value) {
            InformDialog(
                show = true,
                title = "Error",
                message = adminTreeTypeDetailViewModel.state.value.errorMessage.value,
                positiveButtonEnable = true,
                namePositiveButton = "OK",
                onPositiveClick = { dialogState ->
                    dialogState.value = false
                    adminTreeTypeDetailViewModel.state.value.onError.value = false
                }
            )
        }
        if (adminTreeTypeDetailViewModel.state.value.onUpdateSuccess.value) {
            InformDialog(
                show = true,
                title = "Success",
                message = "Update success !",
                positiveButtonEnable = true,
                namePositiveButton = "OK",
                onPositiveClick = { dialogState ->
                    dialogState.value = false
                    adminTreeTypeDetailViewModel.state.value.onUpdateSuccess.value = false
                }
            )
        }
        if (adminTreeTypeDetailViewModel.state.value.onDeleted.value) {
            InformDialog(
                show = true,
                title = "Success",
                message = "Delete Tree Type success !",
                positiveButtonEnable = true,
                namePositiveButton = "OK",
                onPositiveClick = { dialogState ->
                    dialogState.value = false
                    adminTreeTypeDetailViewModel.state.value.onDeleted.value = false
                    adminTreeTypeDetailViewModel.resetSate()
                    navHostController.popBackStack()
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewDetailTreeType() {
    BonsaiAppTheme {
        //AdminTreeTypeDetailScreen()
    }
}