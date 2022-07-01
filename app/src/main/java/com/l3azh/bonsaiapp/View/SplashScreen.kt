package com.l3azh.bonsaiapp.View

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Navigation.BonsaiNavigationTag
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.DarkBlue900
import com.l3azh.bonsaiapp.ui.theme.White
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    navHostController: NavHostController? = null
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(color = White)
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            Card(shape = CircleShape) {
                val configuration = LocalConfiguration.current
                val sizePick = Math.min(configuration.screenWidthDp, configuration.screenHeightDp)
                CircularProgressIndicator(
                    color = DarkBlue900,
                    modifier = Modifier
                        .size((sizePick / 2.5).dp)
                        .align(alignment = Alignment.Center)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_bonsai_splash_150),
                    contentDescription = "Splash icon",
                    modifier = Modifier
                        .size((sizePick / 4).dp)
                        .align(alignment = Alignment.Center)
                )
            }
        }
        LaunchedEffect(true ) {
            delay(5000)
            CoroutineScope(Dispatchers.Main).launch {
                Log.e("Sheet", "SplashScreen: time running" )
                navHostController?.navigate(BonsaiNavigationTag.LoginScreen.name){
                    navHostController.popBackStack()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    BonsaiAppTheme {
        SplashScreen()
    }
}