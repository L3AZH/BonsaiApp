package com.l3azh.bonsaiapp.ViewModel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Api.Response.GetAllTreeGroupByTreeTypeTree
import com.l3azh.bonsaiapp.Model.TreeState
import com.l3azh.bonsaiapp.Model.TreeTypeState
import com.l3azh.bonsaiapp.Repository.TreeRepository
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListTreeByTypeState(
    var listTree: MutableState<List<TreeState>> = mutableStateOf(listOf()),
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var isEmpty: MutableState<Boolean> = mutableStateOf(true),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var onError: MutableState<Boolean> = mutableStateOf(false)
)

@HiltViewModel
class UserListTreeByTypeViewModel @Inject constructor(private val treeRepository: TreeRepository) :
    ViewModel() {
    var state = mutableStateOf(UserListTreeByTypeState())

    fun resetState() {
        state = mutableStateOf(UserListTreeByTypeState())
    }

    fun getListTree(context: Context, uuidTreeType: String) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
                state.value.isEmpty.value = false
            }
            treeRepository.getTreeGroupByTreeType(
                context = context,
                uuidTreeType = uuidTreeType,
                onSuccess = { getListTreeResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state.value.onError.value = false
                        val transform: (GetAllTreeGroupByTreeTypeTree) -> TreeState = { it ->
                            TreeState(
                                it.uuidTree,
                                it.name,
                                it.description,
                                it.price,
                                BonsaiAppUtils.getBitmapFromStringData(it.picture),
                                TreeTypeState(
                                    it.treeType.uuidTreeType,
                                    it.treeType.name,
                                    it.treeType.description
                                )
                            )
                        }
                        state.value.listTree.value = getListTreeResponse.data.listTree.map(transform)
                    }
                },
                onError = { bonsaiErrorResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state.value.onError.value = true
                        state.value.isEmpty.value = true
                        state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                    }
                }
            )
        }
}