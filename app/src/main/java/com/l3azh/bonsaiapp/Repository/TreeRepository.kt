package com.l3azh.bonsaiapp.Repository

import android.content.Context
import com.l3azh.bonsaiapp.Api.BonsaiApi
import com.l3azh.bonsaiapp.Api.Request.CreateTreeRequest
import com.l3azh.bonsaiapp.Api.Request.UpdateTreeRequest
import com.l3azh.bonsaiapp.Api.Response.*
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

    suspend fun getTreeByName(
        context: Context,
        nameTree: String,
        onSuccess: (GetTreeByNameResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = bonsaiApi.getTreeByName(SharePrefUtils.getBearerToken(context), nameTree)
            if (response.isSuccessful) {
                onSuccess(response.body()!!)
            } else {
                onError(BonsaiErrorResponse.convertFromErrorBody(response.errorBody()!!))
            }
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }

    suspend fun getTreeInfo(
        context: Context,
        uuidTree: String,
        onSuccess: (GetTreeResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = bonsaiApi.getTreeInfo(SharePrefUtils.getBearerToken(context), uuidTree)
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

    suspend fun getAllTreeGroupByTreeType(
        context: Context,
        onSuccess: (GetAllTreeGroupByTreeTypeResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response =
                bonsaiApi.getAllTreeGroupByTreeType(SharePrefUtils.getBearerToken(context))
            if (response.isSuccessful) {
                onSuccess(response.body()!!)
            } else {
                onError(BonsaiErrorResponse.convertFromErrorBody(response.errorBody()!!))
            }
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }

    suspend fun getTreeGroupByTreeType(
        context: Context,
        uuidTreeType: String,
        onSuccess: (GetTreeGroupByTreeTypeResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response =
                bonsaiApi.getTreeGroupByTreeType(
                    SharePrefUtils.getBearerToken(context),
                    uuidTreeType
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

    suspend fun updateTree(
        context: Context,
        uuidTree: String,
        request: UpdateTreeRequest,
        onSuccess: (UpdateTreeResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response =
                bonsaiApi.updateTree(SharePrefUtils.getBearerToken(context), uuidTree, request)
            if (response.isSuccessful) {
                onSuccess(response.body()!!)
            } else {
                onError(BonsaiErrorResponse.convertFromErrorBody(response.errorBody()!!))
            }
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }

    suspend fun deleteTree(
        context: Context,
        uuidTree: String,
        onSuccess: (DeleteTreeResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try {
            val response = bonsaiApi.deleteTree(SharePrefUtils.getBearerToken(context), uuidTree)
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