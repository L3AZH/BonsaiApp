package com.l3azh.bonsaiapp.ViewModel

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Api.Response.InfoAccountResponse
import com.l3azh.bonsaiapp.Model.AccountInfoState
import com.l3azh.bonsaiapp.Model.ItemMenuOfAdminMainScreen
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.Repository.AccountRepository
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import com.l3azh.bonsaiapp.ui.theme.GoldLight
import com.l3azh.bonsaiapp.ui.theme.GreenSuperLight
import com.l3azh.bonsaiapp.ui.theme.RoseLight
import com.l3azh.bonsaiapp.ui.theme.defaultDialogButton
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


data class AdminMainMenuState(
    var accountInfo: MutableState<AccountInfoState> = mutableStateOf(
        AccountInfoState(
            "",
            "",
            "",
            "",
            "",
            null
        )
    ),
    val listItem: List<ItemMenuOfAdminMainScreen> = listOf(
        ItemMenuOfAdminMainScreen("Tree", R.drawable.ic_bs_tree, GreenSuperLight),
        ItemMenuOfAdminMainScreen("Bill", R.drawable.ic_bs_bill, RoseLight),
        ItemMenuOfAdminMainScreen("TreeType", R.drawable.ic_bs_note, GoldLight),
        ItemMenuOfAdminMainScreen("Account", R.drawable.ic_bs_add_admin_user, defaultDialogButton)
    ),
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var onPickAndCaptureImage:MutableState<Boolean> = mutableStateOf(false),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var onError: MutableState<Boolean> = mutableStateOf(false)
) {
    fun openDialogPickAndCaptureImage(){
        onPickAndCaptureImage.value = true
    }

    fun closeDialogPickAndCaptureImage(){
        onPickAndCaptureImage.value = false
    }

    fun updateImageAccountInfo(bitmap: Bitmap){
        accountInfo.value.avatar = bitmap
        accountInfo.value = accountInfo.value.copy()
    }
}

@HiltViewModel
class AdminMainMenuViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    val state = mutableStateOf(AdminMainMenuState())


    fun initData(context: Context, email: String) =
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                state.value.isLoading.value = true
            }
            accountRepository.getInfoAccount(context, email, onSuccess = { infoAccountResponse ->
                CoroutineScope(Dispatchers.Main).launch{
                    state.value.isLoading.value = false
                    state.value.onError.value = false
                    state.value.accountInfo.value = AccountInfoState(
                        infoAccountResponse.data.email,
                        infoAccountResponse.data.firstName,
                        infoAccountResponse.data.lastName,
                        infoAccountResponse.data.phoneNumber,
                        infoAccountResponse.data.role,
                        if (infoAccountResponse.data.avatar.isEmpty()) null else
                            BonsaiAppUtils.getBitmapFromStringData(infoAccountResponse.data.avatar)
                    )
                }
            }, onError = { bonsaiErrorResponse->
                CoroutineScope(Dispatchers.Main).launch {
                    state.value.isLoading.value = false
                    state.value.onError.value = true
                    state.value.errorMessage.value = bonsaiErrorResponse.errorMessage
                }
            })
        }
}