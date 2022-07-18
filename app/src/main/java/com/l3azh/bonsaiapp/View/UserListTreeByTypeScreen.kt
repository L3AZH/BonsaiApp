package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.AppBarBackButton
import com.l3azh.bonsaiapp.Component.EmptyPageComponent
import com.l3azh.bonsaiapp.Component.TreeThumbnailComponent
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Navigation.BonsaiNavigationTag
import com.l3azh.bonsaiapp.ViewModel.UserListTreeByTypeViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserListTreeByTypeScreen(
    uuidTreeType: String,
    userListTreeByTypeViewModel: UserListTreeByTypeViewModel,
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
                    userListTreeByTypeViewModel.resetState()
                    navHostController.popBackStack()
                })
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(1f)) {
            if (userListTreeByTypeViewModel.state.value.isEmpty.value) {
                EmptyPageComponent()
            } else {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    modifier = Modifier.padding(10.dp)
                ) {
                    items(userListTreeByTypeViewModel.state.value.listTree.value) { tree ->
                        TreeThumbnailComponent(
                            name = tree.name,
                            price = tree.price.toString(),
                            picture = tree.picture,
                            onClick = {
                                navHostController.navigate("${BonsaiNavigationTag.UserTreeInfoScreen.name}/${tree.uuid}")
                            }
                        )
                    }
                }
            }
            LoadingScreen(isShow = userListTreeByTypeViewModel.state.value.isLoading.value)
            if (userListTreeByTypeViewModel.state.value.onError.value) {
                InformDialog(
                    show = true,
                    title = "Error",
                    message = userListTreeByTypeViewModel.state.value.errorMessage.value,
                    positiveButtonEnable = true,
                    namePositiveButton = "OK",
                    onPositiveClick = { dialogState ->
                        dialogState.value = false
                        userListTreeByTypeViewModel.state.value.onError.value = false
                    }
                )
            }
        }
    }
    LaunchedEffect(key1 = true) {
        userListTreeByTypeViewModel.getListTree(context, uuidTreeType)
    }
}

@Preview
@Composable
fun PreviewUserListTreeByTypeScreen() {
    BonsaiAppTheme {
        // UserListTreeByTypeScreen()
    }
}