package com.l3azh.bonsaiapp.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.l3azh.bonsaiapp.MainActivity
import com.l3azh.bonsaiapp.Model.TreeState
import com.l3azh.bonsaiapp.Model.TreeStateForNavigation
import com.l3azh.bonsaiapp.Model.TreeTypeState
import com.l3azh.bonsaiapp.View.*

enum class BonsaiNavigationTag(nameScreen: String) {
    SplashScreen("SplashScreen"),
    LoginScreen("LoginScreen"),
    RegisterScreen("RegisterScreen"),
    AdminMainMenuScreen("AdminMainMenuScreen"),
    UserMainMenuScreen("UserMainMenuScreen"),
    InfoAccountScreen("InfoAccountScreen"),

    AdminTreeTypeScreen("AdminTreeTypeScreen"),
    AdminTreeTypeDetailScreen("AdminTreeTypeDetailScreen"),
    AdminAddTreeTypeScreen("AdminAddTreeTypeScreen"),

    AdminTreeScreen("AdminTreeScreen"),
    AdminTreeDetailScreen("AdminTreeDetailScreen"),
    AdminAddTreeScree("AdminAddTreeScree"),

    AdminBillScreen("AdminBillScreen"),

    AdminCreateAccountScreen("AdminCreateAccountScreen")
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
        composable(BonsaiNavigationTag.InfoAccountScreen.name) {
            InfoAccountScreen(
                (context as MainActivity).infoAccountViewModel,
                navHostController
            )
        }
        composable(BonsaiNavigationTag.UserMainMenuScreen.name) {
            UserMainMenuScreen(navHostController)
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
        composable(
            "${BonsaiNavigationTag.AdminTreeTypeDetailScreen.name}/{treeTypeDetailInfo}",
            arguments = listOf(navArgument("treeTypeDetailInfo") {
                type = NavType.StringType
            })
        ) {
            val treeTypeDetailInfo =
                Gson().fromJson(
                    it.arguments!!.getString("treeTypeDetailInfo"),
                    TreeTypeState::class.java
                )
            AdminTreeTypeDetailScreen(
                (context as MainActivity).adminTreeTypeDetailViewModel.apply {
                    this.initState(treeTypeDetailInfo)
                },
                navHostController
            )
        }
        composable(
            "${BonsaiNavigationTag.AdminTreeDetailScreen.name}/{uuidTree}",
            arguments = listOf(navArgument("uuidTree") {
                type = NavType.StringType
            })
        ) {
            AdminTreeDetailScreen(
                it.arguments!!.getString("uuidTree")!!,
                (context as MainActivity).adminTreeDetailViewModel,
                navHostController
            )
        }
        composable(BonsaiNavigationTag.AdminCreateAccountScreen.name) {
            AdminCreateAccountScreen(
                (context as MainActivity).adminCreateAccountViewModel,
                navHostController
            )
        }
    }
}