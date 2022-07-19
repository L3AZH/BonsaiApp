package com.l3azh.bonsaiapp.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Db.Entity.CartEntity
import com.l3azh.bonsaiapp.Model.CartItemState
import com.l3azh.bonsaiapp.Model.TreeTypeState
import com.l3azh.bonsaiapp.Repository.CartRepository
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserCartState(
    var listTreeItem: MutableState<MutableList<CartItemState>> = mutableStateOf(mutableStateListOf()),
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var onError: MutableState<Boolean> = mutableStateOf(false),
) {
    fun countTotal():Double{
        var total:Double = 0.00
        listTreeItem.value.forEach { cartItemState ->
            total += (cartItemState.price * cartItemState.quantity)
        }
        return total
    }
}

@HiltViewModel
class UserCartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {
    var state = mutableStateOf(UserCartState())

    fun resetState() {
        state = mutableStateOf(UserCartState())
    }

    fun getAllItemCart() =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            cartRepository.getAllItem(
                onSuccess = { listItem ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        val transForm: (CartEntity) -> CartItemState = { cartEntity ->
                            CartItemState(
                                uuid = cartEntity.uuidTree,
                                name = cartEntity.name,
                                description = cartEntity.description,
                                price = cartEntity.price,
                                picture = BonsaiAppUtils.convertByteArrayToBitmap(cartEntity.picture),
                                type = TreeTypeState(
                                    uuid = cartEntity.uuidType,
                                    name = cartEntity.nameType,
                                    description = ""
                                ),
                                quantity = cartEntity.quantity
                            )
                        }
                        state.value.listTreeItem.value = listItem.map(transForm).toMutableStateList()
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

    fun updateQuantity(cartItemState: CartItemState, index:Int) =
        CoroutineScope(Dispatchers.IO).launch {
            if(cartItemState.quantity <= 0){
                cartRepository.deleteItem(
                    convertCartStateToEntity(cartItemState),
                    onSuccess = {
                        CoroutineScope(Dispatchers.Main).launch {
                            state.value.listTreeItem.value.remove(cartItemState)
                        }
                    },
                    onError = { bonsaiErrorResponse ->
                        CoroutineScope(Dispatchers.Main).launch {
                            state.value.onError.value = true
                            state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                        }
                    }
                )
            } else {
                cartRepository.updateItem(
                    convertCartStateToEntity(cartItemState),
                    onSuccess = {
                        CoroutineScope(Dispatchers.Main).launch {
                            state.value.listTreeItem.value = state.value.listTreeItem.value.map { it ->
                                if(cartItemState.uuid.equals(it.uuid)){
                                    it.copy(quantity = cartItemState.quantity)
                                } else {
                                    it
                                }
                            }.toMutableStateList()
                        }
                    },
                    onError = { bonsaiErrorResponse ->
                        CoroutineScope(Dispatchers.Main).launch {
                            state.value.onError.value = true
                            state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                        }
                    })
            }

        }

    private fun convertCartStateToEntity(cartItemState: CartItemState): CartEntity {
        return CartEntity(
            uuidTree = cartItemState.uuid,
            name = cartItemState.name,
            description = cartItemState.description,
            price = cartItemState.price,
            picture = BonsaiAppUtils.convertBitmapToByteArray(cartItemState.picture!!)!!,
            uuidType = cartItemState.type.uuid,
            nameType = cartItemState.type.name,
            quantity = cartItemState.quantity
        )
    }

}