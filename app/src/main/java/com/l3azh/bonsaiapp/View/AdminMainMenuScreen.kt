package com.l3azh.bonsaiapp.View

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.l3azh.bonsaiapp.Component.InfoAccountComponent
import com.l3azh.bonsaiapp.Component.ItemMenuComponent
import com.l3azh.bonsaiapp.Dialog.InformDialog
import com.l3azh.bonsaiapp.Dialog.LoadingDialog
import com.l3azh.bonsaiapp.MainActivity
import com.l3azh.bonsaiapp.Navigation.BonsaiNavigationTag
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import com.l3azh.bonsaiapp.Util.SharePrefUtils
import com.l3azh.bonsaiapp.ViewModel.AdminMainMenuViewModel
import com.l3azh.bonsaiapp.ui.theme.BonsaiAppTheme
import com.l3azh.bonsaiapp.ui.theme.Green
import com.l3azh.bonsaiapp.ui.theme.GreenLight
import me.bytebeats.views.charts.bar.BarChart
import me.bytebeats.views.charts.bar.BarChartData
import me.bytebeats.views.charts.bar.render.bar.SimpleBarDrawer
import me.bytebeats.views.charts.bar.render.label.SimpleLabelDrawer
import me.bytebeats.views.charts.line.LineChart
import me.bytebeats.views.charts.line.LineChartData
import me.bytebeats.views.charts.line.render.line.SolidLineDrawer
import me.bytebeats.views.charts.line.render.point.FilledCircularPointDrawer
import me.bytebeats.views.charts.line.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.views.charts.line.render.yaxis.SimpleYAxisDrawer
import me.bytebeats.views.charts.simpleChartAnimation

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AdminMainScreen(
    adminMainMenuViewModel: AdminMainMenuViewModel,
    navHostController: NavHostController? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(1f),
        topBar = {
            /*Text(
                text = "Dash Board", style = MaterialTheme.typography.h1.copy(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Green
                ), modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 15.dp, top = 10.dp)
            )*/
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(1f)
        ) {
            LoadingDialog(
                show = adminMainMenuViewModel.state.value.isLoading.value
            )
            if (adminMainMenuViewModel.state.value.onError.value) {
                InformDialog(
                    show = true,
                    title = "Error",
                    message = adminMainMenuViewModel.state.value.errorMessage.value,
                    positiveButtonEnable = true,
                    namePositiveButton = "OK",
                    onPositiveClick = { dialogState ->
                        dialogState.value = false
                        adminMainMenuViewModel.state.value.onError.value = false
                    }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(1f)
            ) {
                InfoAccountComponent(
                    name = adminMainMenuViewModel.state.value.accountInfo.value.lastName,
                    email = adminMainMenuViewModel.state.value.accountInfo.value.email,
                    role = adminMainMenuViewModel.state.value.accountInfo.value.role,
                    avatar = adminMainMenuViewModel.state.value.accountInfo.value.avatar,
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp),
                    onImageUpdate = {
                        navHostController!!.navigate(BonsaiNavigationTag.InfoAccountScreen.name)
                    })
                if (adminMainMenuViewModel.state.value.listTreeOrderQuantity.value.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(20.dp))
                    BarChart(
                        barChartData = BarChartData(
                            bars = adminMainMenuViewModel.state.value.listTreeOrderQuantity.value.map { statisticTreeOrderQuantity ->
                                BarChartData.Bar(
                                    value = statisticTreeOrderQuantity.quantity.toFloat(),
                                    label = statisticTreeOrderQuantity.nameTree,
                                    color = GreenLight
                                )
                            }.toList()
                        ),
                        // Optional properties.
                        modifier = Modifier.fillMaxWidth(1f).padding(horizontal = 50.dp).height(100.dp),
                        animation = simpleChartAnimation(),
                        barDrawer = SimpleBarDrawer(),
                        xAxisDrawer = me.bytebeats.views.charts.bar.render.xaxis.SimpleXAxisDrawer(),
                        yAxisDrawer = me.bytebeats.views.charts.bar.render.yaxis.SimpleYAxisDrawer(),
                        labelDrawer = SimpleLabelDrawer()
                    )
                }
                if (adminMainMenuViewModel.state.value.listTotalBill.value.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(50.dp))
                    LineChart(
                        lineChartData = LineChartData(
                            points = adminMainMenuViewModel.state.value.listTotalBill.value.map { statisticTotalBillState ->
                                LineChartData.Point(
                                    statisticTotalBillState.total.toFloat(),
                                    BonsaiAppUtils.getDateStringWithOutTime(statisticTotalBillState.time)
                                )
                            }.toList()
                        ),
                        // Optional properties.
                        modifier = Modifier.fillMaxWidth(1f).padding(horizontal = 50.dp).height(100.dp),
                        animation = simpleChartAnimation(),
                        pointDrawer = FilledCircularPointDrawer(),
                        lineDrawer = SolidLineDrawer(),
                        xAxisDrawer = SimpleXAxisDrawer(),
                        yAxisDrawer = SimpleYAxisDrawer(),
                        horizontalOffset = 5f
                    )
                }
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    modifier = Modifier.padding(32.dp)
                ) {
                    items(
                        items = adminMainMenuViewModel.state.value.listItem,
                        itemContent = { item ->
                            Box(
                                modifier = Modifier.weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                ItemMenuComponent(
                                    name = item.title,
                                    color = item.color,
                                    icon = item.icon,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .clickable {
                                            when (item.title) {
                                                "Tree" -> navHostController!!
                                                    .navigate(BonsaiNavigationTag.AdminTreeScreen.name)
                                                "Bill" -> navHostController!!
                                                    .navigate(BonsaiNavigationTag.AdminBillScreen.name)
                                                "TreeType" -> navHostController!!
                                                    .navigate(BonsaiNavigationTag.AdminTreeTypeScreen.name)
                                                "Account" -> navHostController!!
                                                    .navigate(BonsaiNavigationTag.AdminCreateAccountScreen.name)
                                            }
                                        }
                                )
                            }
                        })
                }
            }
        }
    }
    LaunchedEffect(key1 = true) {
        adminMainMenuViewModel.initData(context, SharePrefUtils.getEmail(context))
        adminMainMenuViewModel.getTotalBill(context)
        adminMainMenuViewModel.getTreeOrderQuantity(context)
    }
}

@Composable
@Preview
fun PreviewAdminMainScreen() {
    BonsaiAppTheme {
        val context = LocalContext.current
        AdminMainScreen((context as MainActivity).adminMainMenuViewModel)
    }
}