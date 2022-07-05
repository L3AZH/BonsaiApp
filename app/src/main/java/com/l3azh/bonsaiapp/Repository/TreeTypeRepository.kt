package com.l3azh.bonsaiapp.Repository

import android.content.Context
import com.l3azh.bonsaiapp.Api.BonsaiApi
import com.l3azh.bonsaiapp.Api.Response.BonsaiErrorResponse
import com.l3azh.bonsaiapp.Api.Response.GetAllTreeTypeResponse
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import com.l3azh.bonsaiapp.Util.SharePrefUtils
import javax.inject.Inject

class TreeTypeRepository @Inject constructor(private val bonsaiApi: BonsaiApi) {

    suspend fun getAllTreeType(
        context: Context,
        onSuccess: (GetAllTreeTypeResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ){
        try{
            val response = bonsaiApi.getAllTreeType(SharePrefUtils.getBearerToken(context))
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