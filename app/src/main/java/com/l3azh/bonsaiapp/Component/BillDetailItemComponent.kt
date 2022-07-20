package com.l3azh.bonsaiapp.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.l3azh.bonsaiapp.Model.BillDetailState
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@Composable
fun BillDetailItemComponent(
    billDetailState: BillDetailState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(1f), verticalAlignment = Alignment.CenterVertically) {
            Image(
                bitmap = billDetailState.tree.picture.asImageBitmap(),
                contentDescription = "Picture", modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = billDetailState.tree.name, style = MaterialTheme.typography.caption.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(text = "${BonsaiAppUtils.formatDoubleNumber(billDetailState.priceSold)}$")
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "x${billDetailState.quantity}")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.End) {
            Text(text = "${BonsaiAppUtils.formatDoubleNumber(billDetailState.priceSold * billDetailState.quantity)}$")
        }
    }
}

@Composable
@Preview
fun PreviewBillDetailItemComponent() {
    BonsaiAppTheme {
        //BillDetailItemComponent()
    }
}