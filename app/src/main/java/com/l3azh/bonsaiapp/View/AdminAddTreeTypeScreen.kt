package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.AppBarBackButton
import com.l3azh.bonsaiapp.Component.AppBarSaveButton
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@Composable
fun AdminAddTreeTypeScreen(
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppBarBackButton(onClick = {
                    navHostController.popBackStack()
                })
                AppBarSaveButton(onClick = {
                })
            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize(1f)
            .padding(32.dp)) {
            Text(text = "Name Type", style = MaterialTheme.typography.caption.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ))
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = false,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Description",style = MaterialTheme.typography.caption.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ))
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(120.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = false,
                maxLines = 4,
            )
        }
    }
}

@Composable
@Preview
fun PreviewAdminAddTreeTypeScreen(){
    BonsaiAppTheme {
        //AdminAddTreeTypeScreen()
    }
}