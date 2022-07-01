package com.l3azh.bonsaiapp.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.View.UserMainScreen

enum class BonsaiBottomNavigationTag(val nameScreen: String, val title: String, val icon: Int) {
    UserMainScreen("UserMainScreen", "Main", R.drawable.ic_bs_user),
    UserSearchScreen("UserSearchScreen", "Search", R.drawable.ic_bs_user),
    UserCartScreen("UerCartScreen", "Cart", R.drawable.ic_bs_user);


    fun getTitleValue(): String {
        return title
    }

    fun getIconValue(): Int {
        return icon
    }
}

@Composable
fun BonsaiBottomNavHost(
    navHostController: NavHostController,
    modifier: Modifier
) {
    val context = LocalContext.current
    NavHost(
        navController = navHostController,
        startDestination = BonsaiBottomNavigationTag.UserMainScreen.name
    ) {
        composable(BonsaiBottomNavigationTag.UserMainScreen.name) {
            UserMainScreen()
        }
        composable(BonsaiBottomNavigationTag.UserSearchScreen.name) {
        }
        composable(BonsaiBottomNavigationTag.UserCartScreen.name) {
        }
    }
}