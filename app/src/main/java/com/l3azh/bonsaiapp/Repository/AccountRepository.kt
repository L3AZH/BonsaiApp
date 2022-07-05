package com.l3azh.bonsaiapp.Repository

import android.content.Context
import com.l3azh.bonsaiapp.Api.BonsaiApi
import com.l3azh.bonsaiapp.Api.Response.BonsaiErrorResponse
import com.l3azh.bonsaiapp.Api.Response.InfoAccountResponse
import com.l3azh.bonsaiapp.Util.SharePrefUtils
import javax.inject.Inject

class AccountRepository @Inject constructor(private val bonsaiApi: BonsaiApi) {

    suspend fun getInfoAccount(
        context: Context,
        email: String,
        onSuccess: (InfoAccountResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try{
            val response = bonsaiApi.getInfoAccount(SharePrefUtils.getBearerToken(context),email)
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