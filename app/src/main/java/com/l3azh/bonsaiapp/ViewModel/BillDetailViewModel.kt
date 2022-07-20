package com.l3azh.bonsaiapp.ViewModel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Api.Response.BillDetailOfBillInfoResponseData
import com.l3azh.bonsaiapp.Model.BillDetailState
import com.l3azh.bonsaiapp.Model.BillState
import com.l3azh.bonsaiapp.Model.TreeState
import com.l3azh.bonsaiapp.Model.TreeTypeState
import com.l3azh.bonsaiapp.Repository.BillRepository
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BillDetailViewState(
    var billState: MutableState<BillState?> = mutableStateOf(null),
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var onError: MutableState<Boolean> = mutableStateOf(false),
) {
    fun getTotalOfBill():Double{
        var total = 0.0
        if(billState.value != null){
            billState.value!!.listDetail.forEach {
                total += (it.quantity*it.priceSold)
            }
        }
        return total
    }
}

@HiltViewModel
class BillDetailViewModel @Inject constructor(
    private val billRepository: BillRepository
) : ViewModel() {

    var state = mutableStateOf(BillDetailViewState())

    fun resetState() {
        state = mutableStateOf(BillDetailViewState())
    }

    fun getBillInfo(context: Context, uuidBill: String) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            billRepository.getBillInfo(
                context, uuidBill,
                onSuccess = { billInfoResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        val transFormDetail: (BillDetailOfBillInfoResponseData) -> BillDetailState =
                            {
                                BillDetailState(
                                    priceSold = it.priceSold,
                                    quantity = it.quantity,
                                    tree = TreeState(
                                        uuid = it.treeDto.uuidTree,
                                        name = it.treeDto.name,
                                        description = it.treeDto.description,
                                        price = it.treeDto.price,
                                        picture = BonsaiAppUtils.getBitmapFromStringData(it.treeDto.picture),
                                        type = TreeTypeState(
                                            uuid = it.treeDto.treeType.uuidTreeType,
                                            name = it.treeDto.treeType.name,
                                            description = it.treeDto.treeType.description
                                        )
                                    )
                                )
                            }
                        state.value.billState.value = BillState(
                            uuidBill = billInfoResponse.data.billInfo.uuidBill,
                            createDate = billInfoResponse.data.billInfo.createDate,
                            listDetail = billInfoResponse.data.billInfo.listBillDetail.map(
                                transFormDetail
                            ).toList()
                        )
                    }
                },
                onError = { bonsaiErrorResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        CoroutineScope(Dispatchers.Main).launch {
                            state.value.isLoading.value = false
                            state.value.onError.value = true
                            state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                        }
                    }
                })
        }
}