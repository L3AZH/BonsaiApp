package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.EmptyPageComponent
import com.l3azh.bonsaiapp.Component.UserBillItemComponent
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Dialog.LoadingDialog
import com.l3azh.bonsaiapp.Navigation.BonsaiNavigationTag
import com.l3azh.bonsaiapp.ViewModel.UserBillViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@Composable
fun UserBillScreen(
    userBillViewModel: UserBillViewModel,
    navHostController: NavHostController,
    navBottomHostController: NavHostController
) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize(1f)) {
        if (userBillViewModel.state.value.listBill.value.isEmpty()) {
            EmptyPageComponent()
        } else {
            LazyColumn {
                items(userBillViewModel.state.value.listBill.value) { billState ->
                    UserBillItemComponent(
                        billState = billState,
                        onDetailClick = {
                            userBillViewModel.resetState()
                            navHostController.navigate("${BonsaiNavigationTag.BillDetailScreen.name}/${billState.uuidBill}")
                        }
                    )
                }
                item { 
                    Spacer(modifier = Modifier.height(150.dp))
                }
            }
        }
        LoadingDialog(show = userBillViewModel.state.value.isLoading.value)
        if (userBillViewModel.state.value.onError.value) {
            InformDialog(
                show = true,
                title = "Error",
                message = userBillViewModel.state.value.errorMessage.value,
                positiveButtonEnable = true,
                namePositiveButton = "OK",
                onPositiveClick = { dialogState ->
                    dialogState.value = false
                    userBillViewModel.state.value.onError.value = false
                },
                onTapOutSideDialog = { dialogState ->
                    dialogState.value = false
                    userBillViewModel.state.value.onError.value = false
                }
            )
        }
    }
    LaunchedEffect(key1 = true) {
        userBillViewModel.getListBillOfUser(context)
    }
}

@Composable
@Preview
fun PreviewUserBillScreen() {
    BonsaiAppTheme {
        //UserBillScreen()
    }
}