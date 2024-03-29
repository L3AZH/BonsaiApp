package com.l3azh.bonsaiapp.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.l3azh.bonsaiapp.MainActivity
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.View.UserBillScreen
import com.l3azh.bonsaiapp.View.UserCartScreen
import com.l3azh.bonsaiapp.View.UserMainScreen
import com.l3azh.bonsaiapp.View.UserSearchView

enum class BonsaiBottomNavigationTag(val nameScreen: String, val title: String, val icon: Int) {
    UserMainScreen("UserMainScreen", "Main", R.drawable.ic_bs_home),
    UserSearchScreen("UserSearchScreen", "Search", R.drawable.ic_bs_search),
    UserCartScreen(
        "UerCartScreen",
        "Cart",
        R.drawable.ic_bs_shopping_cart
    ),
    UserBillScreen("UserBillScreen", "Bill", R.drawable.ic_bs_note);

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
    navBottomHostController: NavHostController,
    modifier: Modifier
) {
    val context = LocalContext.current
    NavHost(
        navController = navBottomHostController,
        startDestination = BonsaiBottomNavigationTag.UserMainScreen.name
    ) {
        composable(BonsaiBottomNavigationTag.UserMainScreen.name) {
            UserMainScreen(
                (context as MainActivity).userMainViewModel,
                navBottomHostController = navBottomHostController,
                navHostController = navHostController
            )
        }
        composable(BonsaiBottomNavigationTag.UserSearchScreen.name) {
            UserSearchView(
                userSearchViewModel = (context as MainActivity).userSearchViewModel,
                navHostController = navHostController
            )
        }
        composable(BonsaiBottomNavigationTag.UserCartScreen.name) {
            UserCartScreen(
                userCartViewModel = (context as MainActivity).userCartViewModel,
                navHostController = navHostController,
                navBottomHostController = navBottomHostController
            )
        }
        composable(BonsaiBottomNavigationTag.UserBillScreen.name) {
            UserBillScreen(
                userBillViewModel = (context as MainActivity).userBillViewModel,
                navHostController = navHostController,
                navBottomHostController = navBottomHostController
            )
        }
    }
}