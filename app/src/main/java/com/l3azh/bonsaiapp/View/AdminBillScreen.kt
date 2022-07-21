package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.AdminBillItemComponent
import com.l3azh.bonsaiapp.Component.AppBarBackButton
import com.l3azh.bonsaiapp.Component.EmptyPageComponent
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Dialog.LoadingDialog
import com.l3azh.bonsaiapp.Navigation.BonsaiNavigationTag
import com.l3azh.bonsaiapp.ViewModel.AdminBillViewModel
import com.l3azh.bonsaiapp.ui.theme.Green

@Composable
fun AdminBillScreen(
    adminBillViewModel: AdminBillViewModel,
    navHostController: NavHostController
) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(1f),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppBarBackButton(onClick = {
                    adminBillViewModel.resetState()
                    navHostController.popBackStack()
                })
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Bill",
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Green
                    ),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start
                )
            }
        },
    ) {
        Box(modifier = Modifier.fillMaxSize(1f)) {
            if (adminBillViewModel.state.value.listBill.value.isEmpty()) {
                EmptyPageComponent()
            } else {
                LazyColumn {
                    items(adminBillViewModel.state.value.listBill.value) { billState ->
                        AdminBillItemComponent(
                            billState = billState,
                            onDetailClick = {
                                adminBillViewModel.resetState()
                                navHostController.navigate("${BonsaiNavigationTag.BillDetailScreen.name}/${billState.uuidBill}")
                            }
                        )
                    }
                }
            }
            LoadingDialog(show = adminBillViewModel.state.value.isLoading.value)
            if (adminBillViewModel.state.value.onError.value) {
                InformDialog(
                    show = true,
                    title = "Error",
                    message = adminBillViewModel.state.value.errorMessage.value,
                    positiveButtonEnable = true,
                    namePositiveButton = "OK",
                    onPositiveClick = { dialogState ->
                        dialogState.value = false
                        adminBillViewModel.state.value.onError.value = false
                    },
                    onTapOutSideDialog = { dialogState ->
                        dialogState.value = false
                        adminBillViewModel.state.value.onError.value = false
                    }
                )
            }
        }
    }
    LaunchedEffect(key1 = true) {
        adminBillViewModel.getAllBill(context)
    }
}