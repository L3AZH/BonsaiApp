package com.l3azh.bonsaiapp.Repository

import android.content.Context
import com.l3azh.bonsaiapp.Api.BonsaiApi
import com.l3azh.bonsaiapp.Api.Request.CreateTreeRequest
import com.l3azh.bonsaiapp.Api.Response.BonsaiErrorResponse
import com.l3azh.bonsaiapp.Api.Response.CreateTreeResponse
import com.l3azh.bonsaiapp.Api.Response.GetAllTreeResponse
import com.l3azh.bonsaiapp.Util.SharePrefUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TreeRepository @Inject constructor(private val bonsaiApi: BonsaiApi) {

    suspend fun getAllTree(
        context: Context,
        onSuccess: (GetAllTreeResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = bonsaiApi.getAllTree(SharePrefUtils.getBearerToken(context))
            if (response.isSuccessful) {
                onSuccess(response.body()!!)
            } else {
                onError(BonsaiErrorResponse.convertFromErrorBody(response.errorBody()!!))
            }
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }

    suspend fun createNewTree(
        context: Context,
        request: CreateTreeRequest,
        onSuccess: (CreateTreeResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = bonsaiApi.createNewTree(SharePrefUtils.getBearerToken(context), request)
            if (response.isSuccessful) {
                onSuccess(response.body()!!)
            } else {
                onError(BonsaiErrorResponse.convertFromErrorBody(response.errorBody()!!))
            }
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }
}