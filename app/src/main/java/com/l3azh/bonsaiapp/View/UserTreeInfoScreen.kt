package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.AppBarBackButton
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Dialog.LoadingDialog
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ViewModel.UserTreeInfoViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.Green
import com.l3azh.bonsaiapp.ui.theme.GreenLight
import com.l3azh.bonsaiapp.ui.theme.White

@Composable
fun UserTreeInfoScreen(
    uuidTree:String,
    userTreeInfoViewModel: UserTreeInfoViewModel,
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
                    userTreeInfoViewModel.resetState()
                    navHostController.popBackStack()
                })
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(1f)) {
            if(userTreeInfoViewModel.state.value.picture.value == null){
                Icon(
                    painter = painterResource(id = R.drawable.ic_bs_tree),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(250.dp),
                    tint = Color.Black
                )
            } else {
                Image(
                    bitmap = userTreeInfoViewModel.state.value.picture.value!!.asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(250.dp)
                )
            }
            Card(
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                modifier = Modifier
                    .fillMaxSize(1f)
                    .padding(top = 245.dp),
                elevation = 20.dp,
                backgroundColor = GreenLight
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(28.dp)
                ) {
                    Text(
                        text = userTreeInfoViewModel.state.value.nameTree.value,
                        modifier = Modifier.fillMaxWidth(1f),
                        style = MaterialTheme.typography.caption.copy(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Type: ${userTreeInfoViewModel.state.value.treeType.value}", style = MaterialTheme.typography.caption.copy(
                            fontSize = 16.sp,

                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Description: \n - ${userTreeInfoViewModel.state.value.description.value}", style = MaterialTheme.typography.caption.copy(
                            fontSize = 16.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Price: ${userTreeInfoViewModel.state.value.price.value}", style = MaterialTheme.typography.caption.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .weight(1f),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OutlinedButton(
                            onClick = {  }, shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = White)
                        ) {
                            Text(
                                text = "Add to cart",
                                style = MaterialTheme.typography.button.copy(
                                    color = Green
                                )
                            )
                        }
                    }
                }
            }
            LoadingDialog(show = userTreeInfoViewModel.state.value.isLoading.value)
            if (userTreeInfoViewModel.state.value.onError.value) {
                InformDialog(
                    show = true,
                    title = "Error",
                    message = userTreeInfoViewModel.state.value.errorMessage.value,
                    positiveButtonEnable = true,
                    namePositiveButton = "OK",
                    onPositiveClick = { dialogState ->
                        dialogState.value = false
                        userTreeInfoViewModel.state.value.onError.value = false
                    },
                    onTapOutSideDialog = { dialogState ->
                        dialogState.value = false
                        userTreeInfoViewModel.state.value.onError.value = false
                    }
                )
            }
        }
    }
    LaunchedEffect(key1 = true){
        userTreeInfoViewModel.getTreeInfo(context, uuidTree)
    }
}

@Preview
@Composable
fun PreviewUserTreeInfo() {
    BonsaiAppTheme {
        //UserTreeInfoScreen()
    }
}
