package com.l3azh.bonsaiapp.ViewModel

import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserMainScreenViewModel @Inject constructor(
    val accountRepository: AccountRepository) :
    ViewModel() {

}