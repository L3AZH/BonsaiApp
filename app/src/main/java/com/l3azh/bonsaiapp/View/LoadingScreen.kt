package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.DarkBlue900
import com.l3azh.bonsaiapp.ui.theme.White

@Composable
fun LoadingScreen(isShow:Boolean) {
    if(isShow){
        Surface(
            modifier = Modifier.fillMaxSize(1f),
            color = White
        ) {
            Box(
                modifier = Modifier.fillMaxSize(1f)
            ) {
                val configuration = LocalConfiguration.current
                val sizePick =
                    Math.min(configuration.screenWidthDp, configuration.screenHeightDp)
                CircularProgressIndicator(
                    color = DarkBlue900,
                    modifier = Modifier
                        .size((sizePick / 3).dp)
                        .align(alignment = Alignment.Center)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_bs_emptytreeicon),
                    contentDescription = "Splash icon",
                    modifier = Modifier
                        .size((sizePick / 6).dp)
                        .align(alignment = Alignment.Center)
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewLoadingScreen(){
    BonsaiAppTheme {
        LoadingScreen(true)
    }
}