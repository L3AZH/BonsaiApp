package com.l3azh.bonsaiapp.ViewModel

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Api.Request.UpdateTreeRequest
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

class AdminTreeDetailState(
    val uuidTree: MutableState<String> = mutableStateOf(""),
    val nameTree: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf(""),
    val price: MutableState<String> = mutableStateOf(""),
    val picture: MutableState<Bitmap?> = mutableStateOf(null),
    val typeChoose: MutableState<TreeTypeState> = mutableStateOf(TreeTypeState("", "", "")),
    val listType: MutableState<List<TreeTypeState>> = mutableStateOf(listOf()),
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var onPickAndCaptureImage: MutableState<Boolean> = mutableStateOf(false),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var onError: MutableState<Boolean> = mutableStateOf(false),
    var onUdpateSuccess: MutableState<Boolean> = mutableStateOf(false)
) {
    fun openDialogPickAndCaptureImage() {
        onPickAndCaptureImage.value = true
    }

    fun closeDialogPickAndCaptureImage() {
        onPickAndCaptureImage.value = false
    }

    fun updateStateImageChoose(bitmap: Bitmap) {
        picture.value = bitmap
    }
}


@HiltViewModel
class AdminTreeDetailViewModel @Inject constructor(
    private val treeRepository: TreeRepository,
    private val treeTypeRepository: TreeTypeRepository
) : ViewModel() {
    var state = mutableStateOf(AdminTreeDetailState())

    fun resetState() {
        state.value = AdminTreeDetailState()
    }

    fun getTreeInfo(context: Context, uuidTree: String) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            treeRepository.getTreeInfo(context, uuidTree,
                onSuccess = { treeResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state = mutableStateOf(
                            AdminTreeDetailState(
                                uuidTree = mutableStateOf(treeResponse.data.uuidTree),
                                nameTree = mutableStateOf(treeResponse.data.name),
                                description = mutableStateOf(treeResponse.data.description),
                                price = mutableStateOf(treeResponse.data.price.toString()),
                                picture = mutableStateOf(
                                    BonsaiAppUtils.getBitmapFromStringData(
                                        treeResponse.data.picture
                                    )
                                ),
                                typeChoose = mutableStateOf(
                                    TreeTypeState(
                                        treeResponse.data.treeType.uuidTreeType,
                                        treeResponse.data.treeType.name,
                                        treeResponse.data.treeType.description
                                    )
                                )
                            )
                        )
                        getAllTreeType(context)
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

    private fun getAllTreeType(context: Context) =
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
                            state.value.listType.value.forEach { type ->
                                if (type.uuid.equals(state.value.typeChoose.value.uuid)) {
                                    state.value.typeChoose.value = type
                                }
                            }
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

    fun updateTreeInfo(context: Context, uuidTree: String) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            treeRepository.updateTree(
                context, uuidTree,
                UpdateTreeRequest(
                    state.value.nameTree.value,
                    state.value.description.value,
                    if (state.value.price.value.trim().isEmpty()) -1.0
                    else state.value.price.value.toDouble(),
                    BonsaiAppUtils.convertBitmapToStringData(state.value.picture.value!!),
                    state.value.typeChoose.value.uuid
                ),
                onSuccess = { updateTreeResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state.value.onError.value = false
                        state.value.onUdpateSuccess.value = true
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