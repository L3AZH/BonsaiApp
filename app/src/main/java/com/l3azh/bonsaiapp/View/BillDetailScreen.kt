package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.AppBarBackButton
import com.l3azh.bonsaiapp.Component.BillDetailItemComponent
import com.l3azh.bonsaiapp.Component.EmptyPageComponent
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Dialog.LoadingDialog
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import com.l3azh.bonsaiapp.ViewModel.BillDetailViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.defaultDivider

@Composable
fun BillDetailScreen(
    uuidBill: String,
    billDetailViewModel: BillDetailViewModel,
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
                    billDetailViewModel.resetState()
                    navHostController.popBackStack()
                })
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(1f)
        ) {
            if(billDetailViewModel.state.value.billState.value == null){
                EmptyPageComponent()
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize(1f)) {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_bs_note),
                                    contentDescription = "Icon bill",
                                    tint = Color.Black,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Bill ID: ", style = MaterialTheme.typography.caption.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                )
                            }
                            Text(
                                text = billDetailViewModel.state.value.billState.value!!.uuidBill,
                                style = MaterialTheme.typography.caption.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                    items(billDetailViewModel.state.value.billState.value!!.listDetail) { billDetailState ->
                        BillDetailItemComponent(billDetailState = billDetailState)
                    }
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(horizontal = 20.dp)
                        ) {
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .height(1.dp), color = defaultDivider
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(1f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Total: ", style = MaterialTheme.typography.caption.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                )
                                Text(
                                    text = "${BonsaiAppUtils.formatDoubleNumber(billDetailViewModel.state.value.getTotalOfBill())}$",
                                    style = MaterialTheme.typography.caption.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                        }
                    }
                }
            }
        }
        LoadingDialog(show = billDetailViewModel.state.value.isLoading.value)
        if (billDetailViewModel.state.value.onError.value) {
            InformDialog(
                show = true,
                title = "Error",
                message = billDetailViewModel.state.value.errorMessage.value,
                positiveButtonEnable = true,
                namePositiveButton = "OK",
                onPositiveClick = { dialogState ->
                    dialogState.value = false
                    billDetailViewModel.state.value.onError.value = false
                },
                onTapOutSideDialog = { dialogState ->
                    dialogState.value = false
                    billDetailViewModel.state.value.onError.value = false
                }
            )
        }
    }
    LaunchedEffect(key1 = true) {
        billDetailViewModel.getBillInfo(context, uuidBill)
    }
}

@Composable
@Preview
fun PreviewBillDetailScreen() {
    BonsaiAppTheme {
        //BillDetailScreen()
    }
}