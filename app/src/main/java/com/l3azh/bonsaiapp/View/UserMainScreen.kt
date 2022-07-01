package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.l3azh.bonsaiapp.Component.InfoAccountComponent
import com.l3azh.bonsaiapp.Component.TreeTypeThumbnailComponent

@Composable
fun UserMainScreen(){
    Box(modifier = Modifier.fillMaxSize(1f)) {
        Column(modifier = Modifier
            .fillMaxSize(1f)) {
            InfoAccountComponent(
                name = "test",
                email = "asd@gmail.com",
                role = "USER",
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp),
                onImageUpdate = {})
            LazyColumn{
                items(5){
                    TreeTypeThumbnailComponent(name = "testasdsacas")
                }
            }
        }
    }
}