package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.TreeThumbnailComponent
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Navigation.BonsaiNavigationTag
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ViewModel.UserSearchViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserSearchView(
    userSearchViewModel: UserSearchViewModel,
    navHostController: NavHostController,
) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize(1f)) {
        Column(modifier = Modifier.fillMaxSize(1f)) {
            Row(modifier = Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.Center) {
                OutlinedTextField(
                    value = userSearchViewModel.state.value.nameTreeSearch.value,
                    onValueChange = { userSearchViewModel.state.value.nameTreeSearch.value = it },
                    shape = RoundedCornerShape(40.dp),
                    maxLines = 1,
                    placeholder = {
                        Text(text = "Search something here ...")
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bs_search),
                            contentDescription = "IconSearch",
                            modifier = Modifier
                                .size(26.dp)
                                .clickable {
                                    userSearchViewModel.getResultSearch(context)
                                }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
            if (userSearchViewModel.state.value.isEmpty.value) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_bs_emptytreeicon),
                        contentDescription = "Empty Tree",
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Oops! Not Found Anything In Here ...")
                }
            } else {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    modifier = Modifier.padding(10.dp)
                ) {
                    items(userSearchViewModel.state.value.listTreeSearch.value) { tree ->
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
        }
        LoadingScreen(isShow = userSearchViewModel.state.value.isLoading.value)
        if (userSearchViewModel.state.value.onError.value) {
            InformDialog(
                show = true,
                title = "Error",
                message = userSearchViewModel.state.value.errorMessage.value,
                positiveButtonEnable = true,
                namePositiveButton = "OK",
                onPositiveClick = { dialogState ->
                    dialogState.value = false
                    userSearchViewModel.state.value.onError.value = false
                }
            )
        }
    }
}

@Composable
@Preview
fun PreviewUserSearchView() {
    BonsaiAppTheme {
        //UserSearchView()
    }
}