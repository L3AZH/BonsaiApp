package com.l3azh.bonsaiapp.ViewModel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Api.Request.UpdateTreeTypeRequest
import com.l3azh.bonsaiapp.Model.TreeTypeState
import com.l3azh.bonsaiapp.Repository.TreeTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AdminTreeTypeDetailState(
    var uuidTreeType: MutableState<String> = mutableStateOf(""),
    var name: MutableState<String> = mutableStateOf(""),
    var description: MutableState<String> = mutableStateOf(""),
    var onUpdateSuccess: MutableState<Boolean> = mutableStateOf(false),
    var onError: MutableState<Boolean> = mutableStateOf(false),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var onDeleted:MutableState<Boolean> = mutableStateOf(false)
) {
}

@HiltViewModel
class AdminTreeTypeDetailViewModel @Inject constructor(
    private val treeTypeRepository: TreeTypeRepository
) : ViewModel() {

    var state = mutableStateOf(AdminTreeTypeDetailState())

    fun initState(treeTypeDetailInfo: TreeTypeState) {
        state = mutableStateOf(
            AdminTreeTypeDetailState(
                mutableStateOf(treeTypeDetailInfo.uuid),
                mutableStateOf(treeTypeDetailInfo.name),
                mutableStateOf(treeTypeDetailInfo.description)
            )
        )
    }

    fun resetSate() {
        state = mutableStateOf(AdminTreeTypeDetailState())
    }

    fun updateTreeType(context: Context) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            treeTypeRepository.updateTreeType(
                context,
                state.value.uuidTreeType.value,
                UpdateTreeTypeRequest(state.value.name.value, state.value.description.value),
                onSuccess = { updateTreeTypeResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state.value.onError.value = false
                        state.value.onUpdateSuccess.value = true
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

    fun deleteTree(context: Context, uuidTreeType: String) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            treeTypeRepository.deleteTreeType(
                context, uuidTreeType,
                onSuccess = {deleteTreeTypeResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state.value.onDeleted.value = true
                    }
                },
                onError = {bonsaiErrorResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state.value.onError.value = true
                        state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                    }
                })
        }
}