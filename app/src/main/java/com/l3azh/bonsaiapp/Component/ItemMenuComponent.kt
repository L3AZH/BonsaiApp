package com.l3azh.bonsaiapp.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
        Card(shape = RoundedCornerShape(10), backgroundColor = color.copy(alpha = 0.25f)) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(90.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(shape = RoundedCornerShape(20), backgroundColor = color.copy(alpha = 0.25f)) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.size(60.dp).background(color.copy(alpha = 0.25f))
                    ) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = "Icon",
                            modifier = Modifier.size(40.dp),
                            tint = Color.White
                        )
                        Text(
                            text = name,
                            style = MaterialTheme.typography.caption.copy(
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