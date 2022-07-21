package com.l3azh.bonsaiapp.Component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.l3azh.bonsaiapp.Model.BillState
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import com.l3azh.bonsaiapp.ui.theme.GreenLight
import com.l3azh.bonsaiapp.ui.theme.defaultDivider

@Composable
fun AdminBillItemComponent(
    billState: BillState,
    onDetailClick: (BillState) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(horizontal = 30.dp, vertical = 10.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 20.dp
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(vertical = 10.dp, horizontal = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_bs_note),
                                contentDescription = "Icon bill",
                                tint = Color.Black,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Bill ID: ", style = MaterialTheme.typography.caption.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            )
                        }
                        Text(
                            text = billState.uuidBill,
                            style = MaterialTheme.typography.caption.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick = {
                    onDetailClick(billState)
                }, shape = RoundedCornerShape(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Detail")
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bs_arrowhead),
                            contentDescription = "Icon detail",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
            Divider(modifier = Modifier.fillMaxWidth(1f), color = defaultDivider)
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Email: ", style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = billState.email,
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = GreenLight
                    )
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total: ", style = MaterialTheme.typography.caption.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "${BonsaiAppUtils.formatDoubleNumber(billState.getTotal())}$",
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = GreenLight
                    )
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp), horizontalArrangement = Arrangement.End
            ) {
                Text(text = BonsaiAppUtils.getDateString(billState.createDate))
            }
        }
    }
}

