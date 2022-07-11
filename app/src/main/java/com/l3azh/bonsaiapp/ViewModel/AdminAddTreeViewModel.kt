package com.l3azh.bonsaiapp.ViewModel

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Api.Request.CreateTreeRequest
import com.l3azh.bonsaiapp.Api.Response.TreeTypeResponseData
import com.l3azh.bonsaiapp.Model.TreeTypeState
import com.l3azh.bonsaiapp.Repository.TreeRepository
import com.l3azh.bonsaiapp.Repository.TreeTypeRepository
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AdminAddTreeState(
    val listType: MutableState<List<TreeTypeState>> = mutableStateOf(listOf()),
    val nameTree: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf(""),
    val price: MutableState<String> = mutableStateOf(""),
    val picture: MutableState<Bitmap?> = mutableStateOf(null),
    val typeChoose: MutableState<TreeTypeState> = mutableStateOf(TreeTypeState("", "", "")),
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var onPickAndCaptureImage:MutableState<Boolean> = mutableStateOf(false),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var onError: MutableState<Boolean> = mutableStateOf(false),
    var onAddSuccess: MutableState<Boolean> = mutableStateOf(false)
){
    fun openDialogPickAndCaptureImage(){
        onPickAndCaptureImage.value = true
    }

    fun closeDialogPickAndCaptureImage(){
        onPickAndCaptureImage.value = false
    }

    fun updateStateImageChoose(bitmap: Bitmap){
        picture.value = bitmap
    }
}


@HiltViewModel
class AdminAddTreeViewModel @Inject constructor(
    private val treeRepository: TreeRepository,
    private val treeTypeRepository: TreeTypeRepository
    ) : ViewModel() {
    var state = mutableStateOf(AdminAddTreeState())

    fun resetState(){
        state.value = AdminAddTreeState()
    }

    fun getAllTreeType(context: Context) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            treeTypeRepository.getAllTreeType(
                context = context,
                onSuccess = { getAllTreeTypeResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state.value.onError.value = false
                        if (!getAllTreeTypeResponse.data.isEmpty()) {
                            val transform: (TreeTypeResponseData) -> TreeTypeState = { it ->
                                TreeTypeState(it.uuid, it.name, it.description)
                            }
                            state.value.listType.value =
                                getAllTreeTypeResponse.data.map(transform)
                        }
                    }
                },
                onError = { bonsaiErrorResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state.value.onError.value = true
                        state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                    }
                }
            )
        }

    fun createNewType(context: Context) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            treeRepository.createNewTree(
                context = context,
                request = CreateTreeRequest(
                    state.value.nameTree.value,
                    state.value.description.value,
                    state.value.price.value.toDouble(),
                    BonsaiAppUtils.convertBitmapToStringData(state.value.picture.value!!),
                    state.value.typeChoose.value.uuid
                ),
                onSuccess = { createTreeResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state.value.onError.value = false
                        state.value.onAddSuccess.value = true
                    }
                },
                onError = { bonsaiErrorResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state.value.onError.value = true
                        state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                    }
                }
            )
        }
}