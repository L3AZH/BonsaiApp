package com.l3azh.bonsaiapp.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Model.ItemMenuOfAdminMainScreen
import com.l3azh.bonsaiapp.R
import com.l3azh.bonsaiapp.ui.theme.GoldLight
import com.l3azh.bonsaiapp.ui.theme.GreenSuperLight
import com.l3azh.bonsaiapp.ui.theme.RoseLight
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


data class AdminMainMenuState(
    val listItem: List<ItemMenuOfAdminMainScreen> = listOf(
        ItemMenuOfAdminMainScreen("Tree", R.drawable.ic_bs_tree, GreenSuperLight),
        ItemMenuOfAdminMainScreen("Bill", R.drawable.ic_bs_bill, RoseLight),
        ItemMenuOfAdminMainScreen("TreeType", R.drawable.ic_bs_note, GoldLight)
    )
)

@HiltViewModel
class AdminMainMenuViewModel @Inject constructor(): ViewModel(){

    val state = mutableStateOf(AdminMainMenuState())
}