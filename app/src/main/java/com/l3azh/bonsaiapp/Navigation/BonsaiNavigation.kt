package com.l3azh.bonsaiapp.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.l3azh.bonsaiapp.MainActivity
import com.l3azh.bonsaiapp.View.*

enum class BonsaiNavigationTag(nameScreen: String) {
    SplashScreen("SplashScreen"),
    LoginScreen("LoginScreen"),
    RegisterScreen("RegisterScreen"),
    AdminMainMenuScreen("AdminMainMenuScreen"),
    UserMainMenuScreen("UserMainMenuScreen"),

    AdminTreeTypeScreen("AdminTreeTypeScreen"),
    AdminAddTreeTypeScreen("AdminAddTreeTypeScreen"),

    AdminTreeScreen("AdminTreeScreen"),
    AdminAddTreeScree("AdminAddTreeScree"),

    AdminBillScreen("AdminBillScreen")
}

@Composable
fun BonsaiNavHost(
    navHostController: NavHostController,
    modifier: Modifier
) {
    val context = LocalContext.current
    NavHost(
        navController = navHostController,
        startDestination = BonsaiNavigationTag.SplashScreen.name
    ) {
        composable(BonsaiNavigationTag.SplashScreen.name) {
            SplashScreen(navHostController)
        }
        composable(BonsaiNavigationTag.LoginScreen.name) {
            LoginScreen(
                navHostController,
                (context as MainActivity).loginViewModel
            )
        }
        composable(BonsaiNavigationTag.RegisterScreen.name) {
            RegisterScreen((context as MainActivity).registerViewModel, navHostController)
        }
        composable(BonsaiNavigationTag.UserMainMenuScreen.name) {
            UserMainMenuScreen()
        }
        composable(BonsaiNavigationTag.AdminMainMenuScreen.name) {
            AdminMainScreen((context as MainActivity).adminMainMenuViewModel, navHostController)
        }
        composable(BonsaiNavigationTag.AdminTreeTypeScreen.name) {
            AdminTreeTypeScreen((context as MainActivity).adminTreeTypeViewModel, navHostController)
        }
        composable(BonsaiNavigationTag.AdminTreeScreen.name) {
            AdminTreeScreen((context as MainActivity).adminTreeViewModel, navHostController)
        }
        composable(BonsaiNavigationTag.AdminBillScreen.name) {

        }
        composable(BonsaiNavigationTag.AdminAddTreeTypeScreen.name) {
            AdminAddTreeTypeScreen(
                (context as MainActivity).adminAddTreeTypeViewModel,
                navHostController
            )
        }
        composable(BonsaiNavigationTag.AdminAddTreeScree.name) {
            AdminAddTreeScreen(
                (context as MainActivity).adminAddTreeViewModel,
                navHostController
            )
        }
    }
}