package com.l3azh.bonsaiapp.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@Composable
fun AdminTreeTypeItemComponent() {
    Card(
        shape = RoundedCornerShape(20),
        elevation = 10.dp,
        modifier = Modifier.padding(horizontal = 32.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 15.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bs_tree_black),
                contentDescription = "Default Icon",
                modifier = Modifier.size(40.dp)
            )
            Column(modifier = Modifier
                .weight(1f)
                .height(120.dp)
                .padding(8.dp)) {
                Text(
                    text = "Test title !", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ), maxLines = 1
                )
                Text(
                    text = "Test title !", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ), maxLines = 2
                )
                Text(
                    text = "Test Description qwe fasfasfasfasfasfasfasfasaxasas asfasf safasfsafs afsa fasfasfasfa sfsafas asdasfasfasfdsgsdgasfasf asfasfasf", style = MaterialTheme.typography.body1.copy(
                        fontSize = 14.sp,
                    ), maxLines = 4, overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewAdminTreeTypeItemComponent() {
    BonsaiAppTheme {
        AdminTreeTypeItemComponent()
    }

}