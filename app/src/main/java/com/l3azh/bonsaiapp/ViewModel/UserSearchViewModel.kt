package com.l3azh.bonsaiapp.ViewModel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Api.Response.GetTreeResponseByNameData
import com.l3azh.bonsaiapp.Model.TreeState
import com.l3azh.bonsaiapp.Model.TreeTypeState
import com.l3azh.bonsaiapp.Repository.TreeRepository
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserSearchState(
    var nameTreeSearch: MutableState<String> = mutableStateOf(""),
    var listTreeSearch: MutableState<List<TreeState>> = mutableStateOf(listOf()),
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var isEmpty: MutableState<Boolean> = mutableStateOf(true),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var onError: MutableState<Boolean> = mutableStateOf(false)
)

@HiltViewModel
class UserSearchViewModel @Inject constructor(
    private val treeRepository: TreeRepository
) : ViewModel() {

    var state = mutableStateOf(UserSearchState())

    fun resetState() {
        state = mutableStateOf(UserSearchState())
    }

    fun getResultSearch(context: Context) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            treeRepository.getTreeByName(context, state.value.nameTreeSearch.value,
                onSuccess = { getTreeByNameResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        if (getTreeByNameResponse.data.isEmpty()) {
                            state.value.isEmpty.value = true
                        } else {
                            state.value.isEmpty.value = false
                            val transform: (GetTreeResponseByNameData) -> TreeState = { it ->
                                TreeState(
                                    it.uuid,
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
                            state.value.listTreeSearch.value =
                                getTreeByNameResponse.data.map(transform)
                        }
                    }

                },
                onError = { bonsaiErrorResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state.value.onError.value = true
                        state.value.isEmpty.value = true
                        state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                    }

                })
        }
}