package com.l3azh.bonsaiapp.Repository

import android.content.Context
import com.l3azh.bonsaiapp.Api.BonsaiApi
import com.l3azh.bonsaiapp.Api.Request.CreateTreeTypeRequest
import com.l3azh.bonsaiapp.Api.Request.UpdateTreeTypeRequest
import com.l3azh.bonsaiapp.Api.Response.*
import com.l3azh.bonsaiapp.Util.SharePrefUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TreeTypeRepository @Inject constructor(private val bonsaiApi: BonsaiApi) {

    suspend fun getAllTreeType(
        context: Context,
        onSuccess: (GetAllTreeTypeResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try {
            val response = bonsaiApi.getAllTreeType(SharePrefUtils.getBearerToken(context))
            if (response.isSuccessful) {
                onSuccess(response.body()!!)
            } else {
                onError(BonsaiErrorResponse.convertFromErrorBody(response.errorBody()!!))
            }
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }

    suspend fun createNewTreeType(
        context: Context,
        request: CreateTreeTypeRequest,
        onSuccess: (CreateTreeTypeResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response =
                bonsaiApi.createNewTreeType(SharePrefUtils.getBearerToken(context), request)
            if (response.isSuccessful) {
                onSuccess(response.body()!!)
            } else {
                onError(BonsaiErrorResponse.convertFromErrorBody(response.errorBody()!!))
            }
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }

    suspend fun updateTreeType(
        context: Context,
        uuidTreeType: String,
        request: UpdateTreeTypeRequest,
        onSuccess: (UpdateTreeTypeResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = bonsaiApi.updateTreeType(
                SharePrefUtils.getBearerToken(context),
                uuidTreeType,
                request
            )
            if (response.isSuccessful) {
                onSuccess(response.body()!!)
            } else {
                onError(BonsaiErrorResponse.convertFromErrorBody(response.errorBody()!!))
            }
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }

    suspend fun deleteTreeType(
        context: Context,
        uuidTreeType: String,
        onSuccess: (DeleteTreeTypeResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try {
            val response =
                bonsaiApi.deleteTreeType(SharePrefUtils.getBearerToken(context), uuidTreeType)
            if (response.isSuccessful) {
                onSuccess(response.body()!!)
            } else {
                onError(BonsaiErrorResponse.convertFromErrorBody(response.errorBody()!!))
            }
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e));
        }
    }
}