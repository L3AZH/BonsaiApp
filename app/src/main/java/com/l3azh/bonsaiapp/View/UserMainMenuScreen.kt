package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.l3azh.bonsaiapp.Component.BonsaiBottomNavigationComponent
import com.l3azh.bonsaiapp.Navigation.BonsaiBottomNavHost
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.Green

@Composable
fun UserMainMenuScreen(
    navHostController: NavHostController? = null,
    modifier: Modifier = Modifier
){
    val navBottomHostController = rememberNavController()
    Scaffold(
        topBar = {
            Text(
                text = "Welcome to  Bonsai Shop", style = MaterialTheme.typography.h1.copy(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Green
                ), modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 15.dp, top = 10.dp)
            )
        },
        bottomBar = {
            BonsaiBottomNavigationComponent(navBottomController = navBottomHostController)
        },
        modifier = Modifier.fillMaxSize(1f)
    ) {
        BonsaiBottomNavHost(
            navHostController = navHostController!!,
            navBottomHostController = navBottomHostController,
            modifier = Modifier)
    }
}

@Composable
@Preview
fun PreviewUserMainMenuScreen(){
    BonsaiAppTheme {
        UserMainMenuScreen()
    }
}