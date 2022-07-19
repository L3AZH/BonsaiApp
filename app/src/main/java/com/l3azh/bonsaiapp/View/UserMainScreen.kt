package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.InfoAccountComponent
import com.l3azh.bonsaiapp.Component.TreeTypeThumbnailComponent
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Dialog.LoadingDialog
import com.l3azh.bonsaiapp.Navigation.BonsaiNavigationTag
import com.l3azh.bonsaiapp.ViewModel.UserMainViewModel

@Composable
fun UserMainScreen(
    userMainViewModel: UserMainViewModel,
    navBottomHostController: NavHostController,
    navHostController: NavHostController,
) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize(1f)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(1f),
        ) {
            item {
                InfoAccountComponent(
                    name = userMainViewModel.state.value.accountInfo.value.lastName,
                    email = userMainViewModel.state.value.accountInfo.value.email,
                    role = userMainViewModel.state.value.accountInfo.value.role,
                    avatar = userMainViewModel.state.value.accountInfo.value.avatar,
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp),
                    onImageUpdate = {
                        navHostController.navigate(BonsaiNavigationTag.InfoAccountScreen.name)
                    })
            }
            items(userMainViewModel.state.value.listTreeByTreeType.value) { treeByType ->
                TreeTypeThumbnailComponent(
                    treeType = treeByType.treeType,
                    listTree = treeByType.listTree,
                    onLoadMoreClick = { uuidTreeType ->
                        navHostController.navigate("${BonsaiNavigationTag.UserListTreeByTreeTypeScreen.name}/${uuidTreeType}")
                    },
                    onTreeClick = { uuidTree ->
                        navHostController.navigate("${BonsaiNavigationTag.UserTreeInfoScreen.name}/${uuidTree}")
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
        LoadingDialog(
            show = userMainViewModel.state.value.isLoading.value
        )
        if (userMainViewModel.state.value.onError.value) {
            InformDialog(
                show = true,
                title = "Error",
                message = userMainViewModel.state.value.errorMessage.value,
                positiveButtonEnable = true,
                namePositiveButton = "OK",
                onPositiveClick = { dialogState ->
                    dialogState.value = false
                    userMainViewModel.state.value.onError.value = false
                    userMainViewModel.state.value.errorMessage.value = ""
                }
            )
        }
    }
    LaunchedEffect(key1 = true) {
        userMainViewModel.initData(context)
    }
}