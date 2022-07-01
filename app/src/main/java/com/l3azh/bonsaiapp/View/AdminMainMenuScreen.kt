package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.InfoAccountComponent
import com.l3azh.bonsaiapp.Component.ItemMenuComponent
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.Green
import com.l3azh.bonsaiapp.ui.theme.GreenLight

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AdminMainScreen(
    navHostController: NavHostController? = null,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(1f),
        topBar = {
            Text(
                text = "Dash Board", style = MaterialTheme.typography.h1.copy(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Green
                ), modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 15.dp, top = 10.dp)
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(1f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(1f)
            ) {
                InfoAccountComponent(
                    name = "test",
                    email = "asd@gmail.com",
                    role = "USER",
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp),
                    onImageUpdate = {})
                LazyVerticalGrid(cells = GridCells.Fixed(2), modifier = Modifier.padding(32.dp)) {
                    items(4) {
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            ItemMenuComponent(
                                name = "Test",
                                color = GreenLight,
                                icon = R.drawable.ic_bs_user,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewAdminMainScreen() {
    BonsaiAppTheme {
        AdminMainScreen()
    }
}