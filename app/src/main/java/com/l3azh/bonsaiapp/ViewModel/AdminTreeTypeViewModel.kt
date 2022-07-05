package com.l3azh.bonsaiapp.ViewModel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Api.Response.TreeTypeResponseData
import com.l3azh.bonsaiapp.Model.TreeTypeState
import com.l3azh.bonsaiapp.Repository.TreeTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class AdminTreeTypeState(
    var listTreeType: MutableState<List<TreeTypeState>> = mutableStateOf(listOf()),
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var isEmpty: MutableState<Boolean> = mutableStateOf(true),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var onError: MutableState<Boolean> = mutableStateOf(false)
)

@HiltViewModel
class AdminTreeTypeViewModel @Inject constructor(
    private val treeTypeRepository: TreeTypeRepository) :
    ViewModel() {
    var state = mutableStateOf(AdminTreeTypeState())

    fun resetState(){
        state.value = AdminTreeTypeState()
    }

    fun getAllTreeType(context: Context) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
                state.value.isEmpty.value = false
            }
            delay(5000)
            treeTypeRepository.getAllTreeType(
                context = context,
                onSuccess = { getAllTreeTypeResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state.value.onError.value = false
                        if(getAllTreeTypeResponse.data.isEmpty()){
                            state.value.isEmpty.value = true
                        } else {
                            state.value.isEmpty.value = false
                            val transform:(TreeTypeResponseData) -> TreeTypeState = { it ->
                                TreeTypeState(it.uuid, it.name, it.description)
                            }
                            state.value.listTreeType.value = getAllTreeTypeResponse.data.map(transform)
                        }
                    }
                },
                onError = { bonsaiErrorResponse ->
                    state.value.isLoading.value = false
                    state.value.onError.value = true
                    state.value.isEmpty.value = true
                    state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                }
            )
        }
}