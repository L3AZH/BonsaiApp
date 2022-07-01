package com.l3azh.bonsaiapp.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.White

@Composable
fun LoadMoreItemThumbnailComponent() {
    Box(modifier = Modifier.size(150.dp), contentAlignment = Alignment.Center) {
        Card(
            shape = RoundedCornerShape(10),
            elevation = 10.dp,
            backgroundColor = White
        ) {
            Box(modifier = Modifier.wrapContentSize(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.size(140.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_bs_user),
                        contentDescription = "Tree Pic",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(10))
                            .padding(5.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoadMoreThumbnail() {
    BonsaiAppTheme {
        LoadMoreItemThumbnailComponent()
    }
}