package com.l3azh.bonsaiapp.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.GrayLight

@Composable
fun DotLineLinkingListComponent(
    title:String,
    list: List<String>
) {
    Column {
        Text(text = title, modifier = Modifier.padding(top = 10.dp, start = 10.dp))
        for (index in list.indices) {
            Box {
                if (index == 0) {
                    Row(
                        modifier = Modifier.size(48.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Divider(
                            modifier = Modifier
                                .width(2.dp)
                                .height(24.dp)
                                .background(GrayLight)
                        )
                    }
                } else if (index == list.size - 1) {
                    Row(
                        modifier = Modifier.size(48.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    ) {
                        Divider(
                            modifier = Modifier
                                .width(2.dp)
                                .height(24.dp)
                                .background(GrayLight)
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier.size(48.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Divider(
                            modifier = Modifier
                                .width(2.dp)
                                .height(48.dp)
                                .background(GrayLight)
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.size(48.dp), contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_bs_dotwithcircle),
                            contentDescription = "Tree",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Text(text = list[index])
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewDotLineLinkingListCompose() {
    BonsaiAppTheme {
        DotLineLinkingListComponent(
            title = "test123",
            list = listOf("Hello 1", "Hello 2", "Hello 3"))
    }
}