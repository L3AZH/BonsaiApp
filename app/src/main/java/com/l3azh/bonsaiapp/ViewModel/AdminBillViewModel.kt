package com.l3azh.bonsaiapp.ViewModel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Api.Response.BillDetailOfEmailResponseData
import com.l3azh.bonsaiapp.Api.Response.BillOfEmailResponseData
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

data class AdminBillState(
    var listBill: MutableState<List<BillState>> = mutableStateOf(listOf()),
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var onError: MutableState<Boolean> = mutableStateOf(false),
)

@HiltViewModel
class AdminBillViewModel @Inject constructor(
    private val billRepository: BillRepository
):ViewModel() {
    var state = mutableStateOf(AdminBillState())

    fun resetState(){
        state = mutableStateOf(AdminBillState())
    }

    fun getAllBill(context: Context) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            billRepository.getAllBill(
                context,
                onSuccess = { allBillReseponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        val transFormDetail:(BillDetailOfEmailResponseData) -> BillDetailState = {
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
                        val transForm:(BillOfEmailResponseData)->BillState = {
                            BillState(
                                uuidBill = it.uuidBill,
                                createDate = it.createDate,
                                email = it.email,
                                listDetail = it.listBillDetail.map(transFormDetail).toList()
                            )
                        }
                        state.value.listBill.value = allBillReseponse.data.listBill.map(transForm).toList()
                    }
                },
                onError = { bonsaiErrorResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state.value.onError.value = true
                        state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                    }
                })
        }
}