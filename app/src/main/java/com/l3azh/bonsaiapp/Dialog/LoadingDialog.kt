package com.l3azh.bonsaiapp.Dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.DarkBlue900

@Composable
fun LoadingDialog(show: Boolean) {
    if (show) {
        Dialog(onDismissRequest = {}) {
            Card(
                shape = CircleShape
            ) {
                Box(
                    modifier = Modifier.wrapContentHeight()
                ) {
                    val configuration = LocalConfiguration.current
                    val sizePick =
                        Math.min(configuration.screenWidthDp, configuration.screenHeightDp)
                    CircularProgressIndicator(
                        color = DarkBlue900,
                        modifier = Modifier
                            .size((sizePick / 4).dp)
                            .align(alignment = Alignment.Center)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bonsai_splash_150),
                        contentDescription = "Splash icon",
                        modifier = Modifier
                            .size((sizePick / 6).dp)
                            .align(alignment = Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoadingDialog() {
    BonsaiAppTheme() {
        LoadingDialog(show = true)
    }
}