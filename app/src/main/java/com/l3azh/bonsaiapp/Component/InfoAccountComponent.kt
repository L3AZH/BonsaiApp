package com.l3azh.bonsaiapp.Component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.White

@Composable
fun InfoAccountComponent(
    name: String,
    email: String,
    role: String,
    avatar: Bitmap? = null,
    onImageUpdate: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.fillMaxWidth(1f),
        elevation = 10.dp,
        backgroundColor = White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clickable { onImageUpdate() }
            ) {
                if(avatar == null){
                    Image(
                        painter = painterResource(id = R.drawable.ic_bs_user),
                        contentDescription = "User Default",
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.Center)
                            .clip(CircleShape)
                    )
                } else {
                    Image(
                        bitmap = avatar.asImageBitmap(),
                        contentDescription = "User Default",
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.Center)
                            .clip(CircleShape)
                    )
                }
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Image(
                        imageVector = Icons.Default.Create,
                        contentDescription = "UploadImage",
                        modifier = Modifier
                            .size(18.dp)
                            .clip(CircleShape)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Hello $name !", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Email: $email", style = MaterialTheme.typography.body1.copy(
                        fontSize = 14.sp,
                    )
                )
                Text(
                    text = "Role: $role", style = MaterialTheme.typography.body1.copy(
                        fontSize = 14.sp,
                    )
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewInfoAccountComponent() {
    BonsaiAppTheme {
        InfoAccountComponent(
            name = "test",
            email = "asddasdas@gmail.com",
            role = "USE",
            modifier = Modifier,
            onImageUpdate = {}
        )
    }
}