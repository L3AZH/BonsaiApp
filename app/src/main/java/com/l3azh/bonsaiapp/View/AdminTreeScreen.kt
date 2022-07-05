package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.l3azh.bonsaiapp.Component.AppBarBackButton
import com.l3azh.bonsaiapp.Component.DotLineLinkingListComponent
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.Green

@Composable
fun AdminTreeScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(1f),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppBarBackButton(onClick = {})
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Tree",
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Green
                    ),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start
                )
            }
        },
        bottomBar = {

        }
    ) {

        Box(
            modifier = Modifier.fillMaxSize(1f)
        ) {
            LazyColumn {
                items(5){
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_bs_tree),
                            contentDescription = "Picture",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape).border(2.dp, Color.Black, CircleShape)
                        )
                        DotLineLinkingListComponent(
                            title = "test123",
                            list = listOf(
                                "qwdsdsads asfasfsaf asdsad asd adsad",
                                "asdasdsadasdasdqwrqwfsafsadasd qwdqwd",
                                "asdasasdasd",
                                "qwdwqdasfafsfsaf"
                            ),
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewAdminTreeScreen() {
    BonsaiAppTheme {
        AdminTreeScreen()
    }
}