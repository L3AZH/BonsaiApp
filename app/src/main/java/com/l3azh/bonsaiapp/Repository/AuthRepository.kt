package com.l3azh.bonsaiapp.Repository

import com.l3azh.bonsaiapp.Api.BonsaiApi
import com.l3azh.bonsaiapp.Api.Request.LoginRequest
import com.l3azh.bonsaiapp.Api.Response.BonsaiErrorResponse
import com.l3azh.bonsaiapp.Api.Response.LoginResponse
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import javax.inject.Inject

class AuthRepository @Inject constructor(val bonsaiApi: BonsaiApi) {

    suspend fun login(
        request: LoginRequest,
        onSuccess: (LoginResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try{
            val response = bonsaiApi.login(BonsaiAppUtils.getBasicAuth(), request)
            if(response.isSuccessful){
                onSuccess(response.body()!!)
            } else {
                onError(BonsaiErrorResponse.convertFromErrorBody(response.errorBody()!!))
            }
        } catch (e:Exception){
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }
}