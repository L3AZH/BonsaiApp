package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.l3azh.bonsaiapp.Component.DotLineLinkingListComponent
import com.l3azh.bonsaiapp.Component.EmptyPageComponent
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Navigation.BonsaiNavigationTag
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ViewModel.AdminTreeViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.GrayLight
import com.l3azh.bonsaiapp.ui.theme.Green
import com.l3azh.bonsaiapp.ui.theme.White

@Composable
fun AdminTreeScreen(
    adminTreeViewModel: AdminTreeViewModel,
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
                    adminTreeViewModel.resetState()
                    navHostController.popBackStack()
                })
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Tree",
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (!adminTreeViewModel.state.value.isLoading.value) {
                        adminTreeViewModel.resetState()
                        navHostController.navigate(BonsaiNavigationTag.AdminAddTreeScree.name)
                    }
                },
                backgroundColor = if (adminTreeViewModel.state.value.isLoading.value) GrayLight else Green,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bs_addnewbook),
                        contentDescription = "Add New Tree",
                        tint = White,
                        modifier = Modifier
                            .size(48.dp)
                            .padding(start = 15.dp)
                    )
                    Text(
                        text = "New Tree", style = MaterialTheme.typography.caption.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = White
                        ),
                        modifier = Modifier.padding(end = 10.dp)
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(1f)
        ) {
            if (adminTreeViewModel.state.value.isEmpty.value) {
                EmptyPageComponent()
            } else {
                LazyColumn {
                    items(adminTreeViewModel.state.value.listTree.value) { tree ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    adminTreeViewModel.resetState()
                                    navHostController.navigate(
                                        "${BonsaiNavigationTag.AdminTreeDetailScreen.name}/${tree.uuid}"
                                    )
                                }
                        ) {
                            if (tree.picture == null) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_bs_tree),
                                    contentDescription = "Picture",
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(CircleShape)
                                        .border(2.dp, Color.Black, CircleShape)
                                )
                            } else {
                                Image(
                                    bitmap = tree.picture.asImageBitmap(),
                                    contentDescription = "Picture",
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(CircleShape)
                                        .border(2.dp, Color.Black, CircleShape)
                                )
                            }
                            DotLineLinkingListComponent(
                                title = tree.name,
                                list = listOf(
                                    tree.uuid,
                                    tree.price.toString(),
                                    tree.description,
                                    tree.type.name
                                ),
                            )
                        }
                    }
                }
            }
            LoadingScreen(isShow = adminTreeViewModel.state.value.isLoading.value)
            if (adminTreeViewModel.state.value.onError.value) {
                InformDialog(
                    show = true,
                    title = "Error",
                    message = adminTreeViewModel.state.value.errorMessage.value,
                    positiveButtonEnable = true,
                    namePositiveButton = "OK",
                    onPositiveClick = { dialogState ->
                        dialogState.value = false
                        adminTreeViewModel.state.value.onError.value = false
                    }
                )
            }
        }
    }
    LaunchedEffect(key1 = true) {
        adminTreeViewModel.initData(context)
    }
}

@Composable
@Preview
fun PreviewAdminTreeScreen() {
    BonsaiAppTheme {
        //AdminTreeScreen()
    }
}