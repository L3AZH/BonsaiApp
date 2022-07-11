package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.l3azh.bonsaiapp.Component.AppBarBackButton
import com.l3azh.bonsaiapp.Component.AppBarDeleteButton
import com.l3azh.bonsaiapp.Component.AppBarSaveButton
import com.l3azh.bonsaiapp.Component.BonsaiDropDownMenu
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@Composable
fun AdminTreeDetailScreen() {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppBarBackButton(onClick = {

                })
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppBarDeleteButton {}
                    AppBarSaveButton {}
                }
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(1f)) {
            Column(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(32.dp)
            ) {
                Row {
                    Text(
                        text = "UUID: ", style = MaterialTheme.typography.caption.copy(
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = "tesafsaf-asdasfd", style = MaterialTheme.typography.caption.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Picture", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bs_tree),
                        contentDescription = "Picture choose", tint = Color.Black,
                        modifier = Modifier
                            .size(100.dp)
                            .clickable {
                            }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Name Type", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    modifier = Modifier.fillMaxWidth(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = false,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Type", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                BonsaiDropDownMenu<String>(
                    Modifier.fillMaxWidth(1f),
                    listOf("asdsa,asfdsf,rqwrwq"),
                    "asdsad"
                )
                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Price", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {

                    },
                    modifier = Modifier.fillMaxWidth(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = false,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Description", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(120.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = false,
                    maxLines = 4,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
@Preview
fun PreviewAdminTreeDetailScreen() {
    BonsaiAppTheme {
        AdminTreeDetailScreen()
    }
}