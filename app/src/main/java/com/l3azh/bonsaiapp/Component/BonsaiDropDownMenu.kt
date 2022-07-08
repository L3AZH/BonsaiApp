package com.l3azh.bonsaiapp.Component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.GrayLight

@Composable
fun <T> BonsaiDropDownMenu(
    modifier: Modifier = Modifier,
    listItem: List<T>,
    selectedItem: T = listItem[0],
    onSelectedItem: (T) -> Unit = {}
) {
    val isExpand = remember { mutableStateOf(false) }
    var rowSize = remember { mutableStateOf(Size.Zero) }

    Card(border = BorderStroke(1.dp, GrayLight)) {
        Column(modifier = modifier.height(58.dp), verticalArrangement = Arrangement.Center) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .onGloballyPositioned { layoutCoordinates ->
                        rowSize.value = layoutCoordinates.size.toSize()
                    }
            ) {
                Text(
                    text = selectedItem.toString(),
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(start = 10.dp)
                )
                IconButton(onClick = { isExpand.value = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bs_down_arrow),
                        contentDescription = "Drop down icon",
                        modifier = Modifier.size(28.dp)
                    )
                }

            }
            DropdownMenu(
                expanded = isExpand.value,
                onDismissRequest = { isExpand.value = false },
                modifier = Modifier.width(with(LocalDensity.current) { rowSize.value.width.toDp() })
            ) {
                listItem.forEachIndexed { index, item ->
                    DropdownMenuItem(onClick = {
                        onSelectedItem(item)
                        isExpand.value = false
                    }) {
                        Text(
                            text = item.toString(),
                            style = MaterialTheme.typography.caption.copy(
                                fontSize = 14.sp
                            ),
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun PreviewBonsaiDropDownMenu() {
    BonsaiAppTheme {
        BonsaiDropDownMenu<String>(Modifier.fillMaxWidth(1f), listOf("test", "test1", "test2"))
    }
}