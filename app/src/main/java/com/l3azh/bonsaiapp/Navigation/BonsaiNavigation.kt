package com.l3azh.bonsaiapp.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.l3azh.bonsaiapp.MainActivity
import com.l3azh.bonsaiapp.View.LoginScreen
import com.l3azh.bonsaiapp.View.RegisterScreen
import com.l3azh.bonsaiapp.View.SplashScreen

enum class BonsaiNavigationTag(nameScreen:String){
    SplashScreen("SplashScreen"),
    LoginScreen("LoginScreen"),
    RegisterScreen("RegisterScreen")
}

@Composable
fun BonsaiNavHost(
    navHostController: NavHostController,
    modifier: Modifier
){
    val context = LocalContext.current
    NavHost(
        navController = navHostController,
        startDestination = BonsaiNavigationTag.SplashScreen.name
    ){
        composable(BonsaiNavigationTag.SplashScreen.name){
            SplashScreen(navHostController)
        }
        composable(BonsaiNavigationTag.LoginScreen.name){
            LoginScreen(
                navHostController,
                (context as MainActivity).loginViewModel
            )
        }
        composable(BonsaiNavigationTag.RegisterScreen.name){
            RegisterScreen(navHostController)
        }
    }
}