package com.l3azh.bonsaiapp.ViewModel

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Db.Entity.CartEntity
import com.l3azh.bonsaiapp.Repository.CartRepository
import com.l3azh.bonsaiapp.Repository.TreeRepository
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserTreeInfoState(
    val uuidTree: MutableState<String> = mutableStateOf(""),
    val nameTree: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf(""),
    val price: MutableState<String> = mutableStateOf(""),
    val treeTypeName: MutableState<String> = mutableStateOf(""),
    val treeTypeUuid: MutableState<String> = mutableStateOf(""),
    val picture: MutableState<Bitmap?> = mutableStateOf(null),
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var onError: MutableState<Boolean> = mutableStateOf(false),
    var onAddToCartSuccess: MutableState<Boolean> = mutableStateOf(false)
)

@HiltViewModel
class UserTreeInfoViewModel @Inject constructor(
    private val treeRepository: TreeRepository,
    private val cartRepository: CartRepository
) :
    ViewModel() {

    var state = mutableStateOf(UserTreeInfoState())

    fun resetState() {
        state = mutableStateOf(UserTreeInfoState())
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
                        state.value = UserTreeInfoState(
                            uuidTree = mutableStateOf(treeResponse.data.uuidTree),
                            nameTree = mutableStateOf(treeResponse.data.name),
                            description = mutableStateOf(treeResponse.data.description),
                            price = mutableStateOf(treeResponse.data.price.toString()),
                            picture = mutableStateOf(
                                BonsaiAppUtils.getBitmapFromStringData(
                                    treeResponse.data.picture
                                )
                            ),
                            treeTypeName = mutableStateOf(treeResponse.data.treeType.name),
                            treeTypeUuid = mutableStateOf(treeResponse.data.treeType.uuidTreeType)
                        )
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

    fun addToCart() =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            cartRepository.getCartItemById(
                uuidTree = state.value.uuidTree.value,
                onSuccess = { cartEntity ->
                    cartEntity.quantity++
                    CoroutineScope(Dispatchers.IO).launch {
                        cartRepository.updateItem(
                            updateTreeItem = cartEntity,
                            onSuccess = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    state.value.isLoading.value = false
                                    state.value.onAddToCartSuccess.value = true
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
                },
                onError = { bonsaiErrorResponse ->
                    if(bonsaiErrorResponse.code == 500){
                        CoroutineScope(Dispatchers.Main).launch {
                            state.value.isLoading.value = false
                            state.value.onError.value = true
                            state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                        }
                    } else {
                        CoroutineScope(Dispatchers.IO).launch {
                            cartRepository.addNewItem(
                                newTreeItem = CartEntity(
                                    uuidTree = state.value.uuidTree.value,
                                    name = state.value.nameTree.value,
                                    description = state.value.description.value,
                                    price = state.value.price.value.toDouble(),
                                    picture = BonsaiAppUtils.convertBitmapToByteArray(state.value.picture.value!!)!!,
                                    uuidType = state.value.treeTypeUuid.value,
                                    nameType = state.value.treeTypeName.value,
                                    quantity = 1
                                ),
                                onSuccess = {
                                    CoroutineScope(Dispatchers.Main).launch {
                                        state.value.isLoading.value = false
                                        state.value.onAddToCartSuccess.value = true
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
                }
            )
        }
}