package com.l3azh.bonsaiapp.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.l3azh.bonsaiapp.Model.CartItemState
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import com.l3azh.bonsaiapp.ui.theme.*

@Composable
fun CartItemComponent(
    cartItemState: CartItemState,
    onPlusClick: (CartItemState) -> Unit,
    onMinusClick: (CartItemState) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(1f).padding(horizontal = 32.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = GraySuperLight,
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(10.dp)
        ) {
            if (cartItemState.picture == null) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bs_tree),
                    contentDescription = "picture default",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                )
            } else {
                Image(
                    bitmap = cartItemState.picture.asImageBitmap(),
                    contentDescription = "picture of tree",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = cartItemState.name,
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = cartItemState.type.name,
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                    )
                )
                Text(
                    text = "$${BonsaiAppUtils.formatDoubleNumber((cartItemState.price*cartItemState.quantity))}",
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.End
                ) {
                    Card(shape = RoundedCornerShape(10.dp), backgroundColor = White) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_bs_minus),
                                contentDescription = "minus tree",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        onMinusClick(cartItemState)
                                    },
                                tint = GreenLight
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = cartItemState.quantity.toString(),
                                style = MaterialTheme.typography.caption.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.ic_bs_plus),
                                contentDescription = "minus tree",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        onPlusClick(cartItemState)
                                    },
                                tint = Green
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewCartItemComponent() {
    BonsaiAppTheme {

    }
}