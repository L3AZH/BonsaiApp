package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.CartItemComponent
import com.l3azh.bonsaiapp.Component.EmptyPageComponent
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Dialog.LoadingDialog
import com.l3azh.bonsaiapp.ViewModel.UserCartViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@Composable
fun UserCartScreen(
    userCartViewModel: UserCartViewModel,
    navHostController: NavHostController,
    navBottomHostController: NavHostController,
) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize(1f)) {
        if (userCartViewModel.state.value.listTreeItem.value.isEmpty()) {
            EmptyPageComponent()
        } else {
            Column(modifier = Modifier.fillMaxSize(1f)) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    elevation = 10.dp,
                    modifier = Modifier.padding(32.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total: ${userCartViewModel.state.value.countTotal()}$",
                            style = MaterialTheme.typography.caption.copy(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Button(onClick = {
                            userCartViewModel.state.value.onCheckOut.value = true
                        }, shape = RoundedCornerShape(10.dp)) {
                            Text(text = "Checkout")
                        }
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                LazyColumn {
                    itemsIndexed(userCartViewModel.state.value.listTreeItem.value) { index, cartTreeItem ->
                        CartItemComponent(
                            cartItemState = cartTreeItem,
                            onPlusClick = { item ->
                                item.quantity++
                                userCartViewModel.updateQuantity(item, index)
                            }, onMinusClick = { item ->
                                item.quantity--
                                userCartViewModel.updateQuantity(item, index)
                            })
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }
            LoadingDialog(show = userCartViewModel.state.value.isLoading.value)
            if (userCartViewModel.state.value.onError.value) {
                InformDialog(
                    show = true,
                    title = "Error",
                    message = userCartViewModel.state.value.errorMessage.value,
                    positiveButtonEnable = true,
                    namePositiveButton = "OK",
                    onPositiveClick = { dialogState ->
                        dialogState.value = false
                        userCartViewModel.state.value.onError.value = false
                    },
                    onTapOutSideDialog = { dialogState ->
                        dialogState.value = false
                        userCartViewModel.state.value.onError.value = false
                    }
                )
            }
            if (userCartViewModel.state.value.onCheckOut.value) {
                InformDialog(
                    show = true,
                    title = "Confirm",
                    message = "Are you sure to place an order?",
                    positiveButtonEnable = true,
                    negativeButtonEnable = true,
                    namePositiveButton = "OK",
                    nameNegativeButton = "Cancel",
                    onPositiveClick = { dialogState ->
                        dialogState.value = false
                        userCartViewModel.state.value.onCheckOut.value = false
                        userCartViewModel.createBill(context)
                    },
                    onNegativeClick = { dialogState ->
                        dialogState.value = false
                        userCartViewModel.state.value.onCheckOut.value = false
                    }
                )
            }
            if (userCartViewModel.state.value.onCreateBillSuccess.value) {
                InformDialog(
                    show = true,
                    title = "Success",
                    message = "Order Success !",
                    positiveButtonEnable = true,
                    namePositiveButton = "OK",
                    onPositiveClick = { dialogState ->
                        dialogState.value = false
                        userCartViewModel.state.value.onCreateBillSuccess.value = false
                        userCartViewModel.clearCartItem()
                    },
                    onTapOutSideDialog = { dialogState ->
                        dialogState.value = false
                        userCartViewModel.state.value.onCreateBillSuccess.value = false
                        userCartViewModel.clearCartItem()
                    }
                )
            }
        }
    }
    LaunchedEffect(key1 = true) {
        userCartViewModel.getAllItemCart()
    }
}

@Composable
@Preview
fun PreviewUserCartScreen() {
    BonsaiAppTheme {
        //UserCartScreen()
    }
}
