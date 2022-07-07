package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.AdminTreeTypeItemComponent
import com.l3azh.bonsaiapp.Component.AppBarBackButton
import com.l3azh.bonsaiapp.Component.EmptyPageComponent
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Navigation.BonsaiNavigationTag
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ViewModel.AdminTreeTypeViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.GrayLight
import com.l3azh.bonsaiapp.ui.theme.Green
import com.l3azh.bonsaiapp.ui.theme.White

@Composable
fun AdminTreeTypeScreen(
    adminTreeTypeViewModel: AdminTreeTypeViewModel,
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
                    adminTreeTypeViewModel.resetState()
                    navHostController.popBackStack()
                })
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Tree Type",
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
                    if (!adminTreeTypeViewModel.state.value.isLoading.value) {
                        adminTreeTypeViewModel.resetState()
                        navHostController.navigate(BonsaiNavigationTag.AdminAddTreeTypeScreen.name)
                    }
                },
                backgroundColor = if (adminTreeTypeViewModel.state.value.isLoading.value) GrayLight else Green,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bs_addnewbook),
                        contentDescription = "Add New Tree Type",
                        tint = White,
                        modifier = Modifier
                            .size(48.dp)
                            .padding(start = 15.dp)
                    )
                    Text(
                        text = "New Type", style = MaterialTheme.typography.caption.copy(
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
        LoadingScreen(isShow = adminTreeTypeViewModel.state.value.isLoading.value)
        if (adminTreeTypeViewModel.state.value.onError.value) {
            InformDialog(
                show = true,
                title = "Error",
                message = adminTreeTypeViewModel.state.value.errorMessage.value,
                positiveButtonEnable = true,
                namePositiveButton = "OK",
                onPositiveClick = { dialogState ->
                    dialogState.value = false
                    adminTreeTypeViewModel.state.value.onError.value = false
                }
            )
        }
        if (adminTreeTypeViewModel.state.value.isEmpty.value) {
            EmptyPageComponent()
        } else {
            Box(modifier = Modifier.fillMaxSize(1f)) {
                LazyColumn {
                    items(items = adminTreeTypeViewModel.state.value.listTreeType.value) {
                        AdminTreeTypeItemComponent()
                    }
                }
            }
        }
    }
    LaunchedEffect(key1 = true) {
        adminTreeTypeViewModel.getAllTreeType(context)
    }
}


@Composable
@Preview
fun PreviewAdminTreeTypeScreen() {
    BonsaiAppTheme {
        //AdminTreeTypeScreen()
    }
}