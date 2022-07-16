package com.l3azh.bonsaiapp.ViewModel

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Api.Request.UpdateAccountRequest
import com.l3azh.bonsaiapp.Repository.AccountRepository
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import com.l3azh.bonsaiapp.Util.SharePrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

data class InfoAccountState(
    var email:MutableState<String> = mutableStateOf(""),
    var firstName:MutableState<String> = mutableStateOf(""),
    var lastName:MutableState<String> = mutableStateOf(""),
    var phoneNumber:MutableState<String> = mutableStateOf(""),
    var avatar:MutableState<Bitmap?> = mutableStateOf(null),
    var isUpdateSuccess:MutableState<Boolean> = mutableStateOf(false),
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var onPickAndCaptureImage: MutableState<Boolean> = mutableStateOf(false),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var onError: MutableState<Boolean> = mutableStateOf(false),
){
    fun openDialogPickAndCaptureImage(){
        onPickAndCaptureImage.value = true
    }

    fun closeDialogPickAndCaptureImage(){
        onPickAndCaptureImage.value = false
    }

    fun updateStateImageChoose(bitmap: Bitmap){
        avatar.value = bitmap
    }
}

@HiltViewModel
class InfoAccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel(){

    var state = mutableStateOf(InfoAccountState())

    fun resetState(){
        state = mutableStateOf(InfoAccountState())
    }

    fun getInfoAccount(context: Context) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            accountRepository.getInfoAccount(context, SharePrefUtils.getEmail(context),
                onSuccess = { infoAccountResponse ->
                    CoroutineScope(Dispatchers.Main).launch {
                        state.value.isLoading.value = false
                        state.value.email.value = infoAccountResponse.data.email
                        state.value.firstName.value = infoAccountResponse.data.firstName
                        state.value.lastName.value = infoAccountResponse.data.lastName
                        state.value.phoneNumber.value = infoAccountResponse.data.phoneNumber
                        state.value.avatar.value = BonsaiAppUtils.getBitmapFromStringData(infoAccountResponse.data.avatar)
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

    fun updateInfoAccount(context: Context) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = false
            }
            accountRepository.updateInfo(context, infoUpdate = UpdateAccountRequest(
                state.value.firstName.value,
                state.value.lastName.value,
                state.value.phoneNumber.value,
                BonsaiAppUtils.convertBitmapToStringData(state.value.avatar.value!!)
            ), onSuccess = { updateAccountInfoResponse ->
                CoroutineScope(Dispatchers.Main).launch {
                    state.value.isLoading.value = false
                    state.value.isUpdateSuccess.value = true
                }

            }, onError = { bonsaiErrorResponse ->
                CoroutineScope(Dispatchers.Main).launch {
                    state.value.isLoading.value = false
                    state.value.onError.value = false
                    state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                }
            })
        }
}