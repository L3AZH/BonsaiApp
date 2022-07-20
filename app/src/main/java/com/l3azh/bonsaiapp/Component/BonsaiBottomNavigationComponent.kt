package com.l3azh.bonsaiapp.Component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.l3azh.bonsaiapp.Navigation.BonsaiBottomNavigationTag
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.GrayLight
import com.l3azh.bonsaiapp.ui.theme.GreenLight
import com.l3azh.bonsaiapp.ui.theme.White

@Composable
fun BonsaiBottomNavigationComponent(
    navBottomController: NavController
) {
    val items = listOf(
        BonsaiBottomNavigationTag.UserMainScreen,
        BonsaiBottomNavigationTag.UserSearchScreen,
        BonsaiBottomNavigationTag.UserBillScreen,
        BonsaiBottomNavigationTag.UserCartScreen,
    )
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp,
        modifier = Modifier.padding(15.dp)
    ) {
        BottomNavigation(
            backgroundColor = White,
            contentColor = GreenLight,
        ) {
            val navBackStackEntry by navBottomController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.title,
                            modifier = Modifier.size(25.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 9.sp
                        )
                    },
                    selectedContentColor = GreenLight,
                    unselectedContentColor = GrayLight,
                    alwaysShowLabel = true,
                    selected = currentRoute == item.name,
                    onClick = {
                        navBottomController.navigate(item.name) {

                            navBottomController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewBonsaiBottomNavigation() {
    BonsaiAppTheme {
        val navHostController = rememberNavController()
        BonsaiBottomNavigationComponent(navHostController)
    }
}