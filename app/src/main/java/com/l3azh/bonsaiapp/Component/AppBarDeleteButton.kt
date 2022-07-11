package com.l3azh.bonsaiapp.Component

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.Green

@Composable
fun AppBarDeleteButton(onClick:()->Unit){
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_bs_delete),
            contentDescription = "App Bar Back Button",
            tint = Green,
            modifier = Modifier.size(36.dp)
        )
    }
}

@Preview
@Composable
fun PreviewAppBarDeleteButton(){
    BonsaiAppTheme {
        AppBarDeleteButton {

        }
    }
}
