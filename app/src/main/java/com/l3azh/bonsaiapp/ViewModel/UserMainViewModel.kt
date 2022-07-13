package com.l3azh.bonsaiapp.ViewModel

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Api.Response.GetAllTreeGroupByTreeTypeData
import com.l3azh.bonsaiapp.Model.AccountInfoState
import com.l3azh.bonsaiapp.Model.TreeByTreeTypeState
import com.l3azh.bonsaiapp.Model.TreeState
import com.l3azh.bonsaiapp.Model.TreeTypeState
import com.l3azh.bonsaiapp.Repository.AccountRepository
import com.l3azh.bonsaiapp.Repository.TreeRepository
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import com.l3azh.bonsaiapp.Util.SharePrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

data class UserMainState(
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
    var listTreeByTreeType: MutableState<List<TreeByTreeTypeState>> = mutableStateOf(listOf()),
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var onPickAndCaptureImage: MutableState<Boolean> = mutableStateOf(false),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var onError: MutableState<Boolean> = mutableStateOf(false),
) {
    fun openDialogPickAndCaptureImage() {
        onPickAndCaptureImage.value = true
    }

    fun closeDialogPickAndCaptureImage() {
        onPickAndCaptureImage.value = false
    }

    fun updateImageAccountInfo(bitmap: Bitmap) {
        accountInfo.value.avatar = bitmap
        accountInfo.value = accountInfo.value.copy()
    }
}

@HiltViewModel
class UserMainViewModel @Inject constructor(
    private val treeRepository: TreeRepository,
    private val accountRepository: AccountRepository,
) : ViewModel() {

    var state = mutableStateOf(UserMainState())

    fun resetSate(){
        state = mutableStateOf(UserMainState())
    }

    fun initData(context: Context) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            val listJob: MutableList<Job> = mutableListOf()
            listJob.add(launch {
                accountRepository.getInfoAccount(
                    context, SharePrefUtils.getEmail(context),
                    onSuccess = { infoAccountResponse ->
                        state.value.accountInfo.value = AccountInfoState(
                            infoAccountResponse.data.email,
                            infoAccountResponse.data.firstName,
                            infoAccountResponse.data.lastName,
                            infoAccountResponse.data.phoneNumber,
                            infoAccountResponse.data.role,
                            if (infoAccountResponse.data.avatar.isEmpty()) null else
                                BonsaiAppUtils.getBitmapFromStringData(infoAccountResponse.data.avatar)
                        )
                    },
                    onError = { bonsaiErrorResponse ->
                        state.value.onError.value = true
                        if (state.value.errorMessage.value.isEmpty()) {
                            state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                        } else {
                            state.value.errorMessage.value = state.value.errorMessage.value +
                                    "\n${bonsaiErrorResponse.errorMessage}"
                        }
                    })
            })
            listJob.add(launch {
                treeRepository.getAllTreeGroupByTreeType(context,
                    onSuccess = { getAllTreeGroupByTreeTypeResponse ->
                        val transform: (GetAllTreeGroupByTreeTypeData) -> TreeByTreeTypeState = {
                            val listTree: MutableList<TreeState> = mutableListOf()
                            for (treeResponse in it.listTree) {
                                listTree.add(
                                    TreeState(
                                        treeResponse.uuidTree,
                                        treeResponse.name,
                                        treeResponse.description,
                                        treeResponse.price,
                                        BonsaiAppUtils.getBitmapFromStringData(treeResponse.picture),
                                        TreeTypeState(
                                            treeResponse.treeType.uuidTreeType,
                                            treeResponse.treeType.name,
                                            treeResponse.treeType.description
                                        )
                                    )
                                )
                            }
                            TreeByTreeTypeState(
                                treeType = TreeTypeState(
                                    it.treeTypeDto.uuidTreeType,
                                    it.treeTypeDto.name,
                                    it.treeTypeDto.description),
                                listTree = listTree
                            )
                        }
                        state.value.listTreeByTreeType.value =
                            getAllTreeGroupByTreeTypeResponse.data.map(transform)
                    },
                    onError = { bonsaiErrorResponse ->
                        state.value.onError.value = true
                        if (state.value.errorMessage.value.isEmpty()) {
                            state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                        } else {
                            state.value.errorMessage.value = state.value.errorMessage.value +
                                    "\n${bonsaiErrorResponse.errorMessage}"
                        }
                    })
            })
            listJob.joinAll()
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = false
            }
        }
}