package com.l3azh.bonsaiapp.ViewModel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Api.Request.CreateTreeTypeRequest
import com.l3azh.bonsaiapp.Repository.TreeTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AdminAddTreeTypeState(
    val nameType: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf(""),
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var onError: MutableState<Boolean> = mutableStateOf(false)
)

@HiltViewModel
class AdminAddTreeTypeViewModel @Inject constructor(private val treeTypeRepository: TreeTypeRepository) :
    ViewModel() {

    var state = mutableStateOf(AdminAddTreeTypeState())

    fun resetState(){
        state.value = AdminAddTreeTypeState()
    }

    fun saveNewTreeType(context: Context, onSaveSuccess: (String) -> Unit) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            treeTypeRepository.createNewTreeType(context,
                CreateTreeTypeRequest(state.value.nameType.value, state.value.description.value),
                onSuccess = { createTreeTypeResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state.value.onError.value = false
                        onSaveSuccess(createTreeTypeResponse.message)
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