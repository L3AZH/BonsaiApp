package com.l3azh.bonsaiapp.Component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.White


@Composable
fun TreeThumbnailComponent(
    name: String,
    price: String,
    picture: Bitmap? = null,
    onClick: () -> Unit
) {
    Box(modifier = Modifier
        .size(150.dp)
        .clickable {
            onClick()
        }, contentAlignment = Alignment.Center) {
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
                    if (picture == null) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_bs_tree_black),
                            contentDescription = "Tree Pic",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(10))
                                .padding(5.dp)
                        )
                    } else {
                        Image(
                            bitmap = picture.asImageBitmap(),
                            contentDescription = "Tree Pic",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(10))
                                .padding(5.dp)
                        )
                    }
                    Text(
                        text = name,
                        style = MaterialTheme.typography.caption.copy(),
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = price,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun PreviewTreThumbnailComponent() {
    BonsaiAppTheme {
        TreeThumbnailComponent(name = "testasd", price = "12315", onClick = {})
    }
}