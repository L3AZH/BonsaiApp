package com.l3azh.bonsaiapp.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.l3azh.bonsaiapp.Api.Request.SignUpRequest
import com.l3azh.bonsaiapp.Repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegisterState(
    var email: MutableState<String> = mutableStateOf(""),
    var firstName: MutableState<String> = mutableStateOf(""),
    var lastName: MutableState<String> = mutableStateOf(""),
    var phoneNumber: MutableState<String> = mutableStateOf(""),
    var password: MutableState<String> = mutableStateOf(""),
    var confirmPassword: MutableState<String> = mutableStateOf(""),
    var onError: MutableState<Boolean> = mutableStateOf(false),
    var errorMessage: MutableState<String> = mutableStateOf(""),
    var isLoading: MutableState<Boolean> = mutableStateOf(false)
)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var state = mutableStateOf(RegisterState())

    fun registerUser(onSignUpSuccess: () -> Unit) =
        CoroutineScope(Dispatchers.IO).launch {
            val registerRequest:SignUpRequest
            if(state.value.password.value.equals(state.value.confirmPassword.value)){
                registerRequest = SignUpRequest(
                    email = state.value.email.value,
                    firstName = state.value.firstName.value,
                    lastName = state.value.lastName.value,
                    phoneNumber = state.value.phoneNumber.value,
                    password = state.value.password.value,
                    role = "USER"
                )
                CoroutineScope(Dispatchers.IO).launch {
                    state.value.isLoading.value = true
                }
                repository.register(
                    request = registerRequest,
                    onSuccess = { registerSuccessResponse ->
                        CoroutineScope(Dispatchers.IO).launch {
                            state.value.isLoading.value = false
                        }
                        onSignUpSuccess()
                    },
                    onError = { registerErrorResponse ->
                        CoroutineScope(Dispatchers.IO).launch {
                            state.value.isLoading.value = false
                            state.value.onError.value = true
                            state.value.errorMessage.value = registerErrorResponse.errorMessage
                        }
                    })
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    state.value.onError.value = true
                    state.value.errorMessage.value = "Password is not match"
                }
            }
        }
}