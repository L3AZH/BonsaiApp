package com.l3azh.bonsaiapp.ViewModel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Api.Response.TotalBillByDayResponseData
import com.l3azh.bonsaiapp.Api.Response.TreeQuantityOrderByDayResponseData
import com.l3azh.bonsaiapp.Model.AccountInfoState
import com.l3azh.bonsaiapp.Model.ItemMenuOfAdminMainScreen
import com.l3azh.bonsaiapp.Model.StatisticTotalBillState
import com.l3azh.bonsaiapp.Model.StatisticTreeQuantityState
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.Repository.AccountRepository
import com.l3azh.bonsaiapp.Repository.StatisticRepository
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import com.l3azh.bonsaiapp.ui.theme.GoldLight
import com.l3azh.bonsaiapp.ui.theme.GreenSuperLight
import com.l3azh.bonsaiapp.ui.theme.RoseLight
import com.l3azh.bonsaiapp.ui.theme.defaultDialogButton
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


data class AdminMainMenuState(
    var accountInfo: MutableState<AccountInfoState> = mutableStateOf(
        AccountInfoState(
            "",
            "",
            "",
            "",
            "",
            null
        )
    ),
    val listItem: List<ItemMenuOfAdminMainScreen> = listOf(
        ItemMenuOfAdminMainScreen("Tree", R.drawable.ic_bs_tree, GreenSuperLight),
        ItemMenuOfAdminMainScreen("Bill", R.drawable.ic_bs_bill, RoseLight),
        ItemMenuOfAdminMainScreen("TreeType", R.drawable.ic_bs_note, GoldLight),
        ItemMenuOfAdminMainScreen("Account", R.drawable.ic_bs_add_admin_user, defaultDialogButton)
    ),
    var listTreeOrderQuantity: MutableState<List<StatisticTreeQuantityState>> = mutableStateOf(
        mutableStateListOf()
    ),
    var listTotalBill: MutableState<List<StatisticTotalBillState>> = mutableStateOf(
        mutableStateListOf()
    ),
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var onError: MutableState<Boolean> = mutableStateOf(false)
)

@HiltViewModel
class AdminMainMenuViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val statisticRepository: StatisticRepository
) : ViewModel() {

    val state = mutableStateOf(AdminMainMenuState())


    fun initData(context: Context, email: String) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            accountRepository.getInfoAccount(context, email, onSuccess = { infoAccountResponse ->
                CoroutineScope(Dispatchers.Main).launch {
                    state.value.isLoading.value = false
                    state.value.onError.value = false
                    state.value.accountInfo.value = AccountInfoState(
                        infoAccountResponse.data.email,
                        infoAccountResponse.data.firstName,
                        infoAccountResponse.data.lastName,
                        infoAccountResponse.data.phoneNumber,
                        infoAccountResponse.data.role,
                        if (infoAccountResponse.data.avatar.isEmpty()) null else
                            BonsaiAppUtils.getBitmapFromStringData(infoAccountResponse.data.avatar)
                    )
                }
            }, onError = { bonsaiErrorResponse ->
                CoroutineScope(Dispatchers.Main).launch {
                    state.value.isLoading.value = false
                    state.value.onError.value = true
                    state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                }
            })
        }

    fun getTreeOrderQuantity(context: Context) =
        CoroutineScope(Dispatchers.IO).launch {
            statisticRepository.getTreeQuantityOrderByDay(
                context, 7,
                onSuccess = { treeQuantityOrderByDayResponse ->
                    val transForm: (TreeQuantityOrderByDayResponseData) -> StatisticTreeQuantityState =
                        {
                            StatisticTreeQuantityState(
                                uuidTree = it.uuidTree,
                                nameTree = it.nameTree,
                                quantity = it.quantity
                            )
                        }
                    state.value.listTreeOrderQuantity.value =
                        treeQuantityOrderByDayResponse.data.map(transForm).toMutableStateList()
                },
                onError = { bonsaiErrorResponse ->

                })
        }

    fun getTotalBill(context: Context) =
        CoroutineScope(Dispatchers.IO).launch {
            statisticRepository.getTotalAmountBillByDay(
                context, 7,
                onSuccess = { totalBillByDayResponse ->
                    val transForm: (TotalBillByDayResponseData) -> StatisticTotalBillState = {
                        StatisticTotalBillState(
                            time = it.totalBillTime,
                            total = it.totalBill
                        )
                    }
                    state.value.listTotalBill.value =
                        totalBillByDayResponse.data.map(transForm).toMutableStateList()
                },
                onError = { bonsaiErrorResponse ->

                })
        }
}