package com.l3azh.bonsaiapp.Component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.l3azh.bonsaiapp.Model.TreeState
import com.l3azh.bonsaiapp.Model.TreeTypeState
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme

@Composable
fun TreeTypeThumbnailComponent(
    treeType: TreeTypeState,
    listTree: List<TreeState>,
    onLoadMoreClick: (String) -> Unit,
    onTreeClick:(String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth(1f)) {
        Text(
            text = treeType.name, style = MaterialTheme.typography.caption.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ), modifier = Modifier.padding(vertical = 20.dp, horizontal = 15.dp)
        )
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            if (listTree.size > 5) {
                for (index in 0..4) {
                    TreeThumbnailComponent(
                        name = listTree[index].name,
                        price = listTree[index].price.toString(),
                        picture = listTree[index].picture,
                        onClick = {
                            onTreeClick(listTree[index].uuid)
                        }
                    )
                }
                LoadMoreItemThumbnailComponent(onClick = { onLoadMoreClick(treeType.uuid) })
            } else {
                for (tree in listTree) {
                    TreeThumbnailComponent(
                        name = tree.name,
                        price = tree.price.toString(),
                        picture = tree.picture,
                        onClick = {
                            onTreeClick(tree.uuid)
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewTreeTypeThumbnailComponent() {
    BonsaiAppTheme {
        //TreeTypeThumbnailComponent("TEst123")
    }
}