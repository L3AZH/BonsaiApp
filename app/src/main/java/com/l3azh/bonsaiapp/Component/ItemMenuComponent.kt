package com.l3azh.bonsaiapp.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.GreenLight
import com.l3azh.bonsaiapp.ui.theme.White

@Composable
fun ItemMenuComponent(
    name: String,
    color: Color,
    icon: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.size(100.dp), contentAlignment = Alignment.Center) {
        Card(shape = RoundedCornerShape(10), backgroundColor = color.copy(alpha = 0.5f)) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(shape = RoundedCornerShape(10), backgroundColor = color) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.size(60.dp)
                    ) {
                        Image(
                            painter = painterResource(id = icon),
                            contentDescription = "Icon",
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = name, style = MaterialTheme.typography.caption.copy(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = White
                            ),
                        )

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewItemMenuComponent() {
    BonsaiAppTheme {
        ItemMenuComponent(name = "Test", color = GreenLight, icon = R.drawable.ic_bs_user)
    }
}