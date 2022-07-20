package com.l3azh.bonsaiapp.Repository

import android.content.Context
import com.l3azh.bonsaiapp.Api.BonsaiApi
import com.l3azh.bonsaiapp.Api.Request.CreateBillRequest
import com.l3azh.bonsaiapp.Api.Response.*
import com.l3azh.bonsaiapp.Util.SharePrefUtils
import javax.inject.Inject

class BillRepository @Inject constructor(private val bonsaiApi: BonsaiApi) {

    suspend fun createBill(
        context: Context,
        email: String,
        request: CreateBillRequest,
        onSuccess: (CreateBillResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try {
            val response = bonsaiApi.createBill(SharePrefUtils.getBearerToken(context), email, request)
            if(response.isSuccessful){
                onSuccess(response.body()!!)
            } else {
                onError(BonsaiErrorResponse.convertFromErrorBody(response.errorBody()!!))
            }
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }

    suspend fun getAllBill(
        context: Context,
        onSuccess: (AllBillResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try {
            val response = bonsaiApi.getAllBill(SharePrefUtils.getBearerToken(context))
            if(response.isSuccessful){
                onSuccess(response.body()!!)
            } else {
                onError(BonsaiErrorResponse.convertFromErrorBody(response.errorBody()!!))
            }
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }

    suspend fun getListBillOfAccount(
        context: Context,
        email: String,
        onSuccess: (BillOfEmailResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try {
            val response = bonsaiApi.getBillOfAccount(SharePrefUtils.getBearerToken(context), email)
            if(response.isSuccessful){
                onSuccess(response.body()!!)
            } else {
                onError(BonsaiErrorResponse.convertFromErrorBody(response.errorBody()!!))
            }
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }

    suspend fun getBillInfo(
        context: Context,
        uuidBill: String,
        onSuccess: (BillInfoResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try {
            val response = bonsaiApi.getBillDetail(SharePrefUtils.getBearerToken(context), uuidBill)
            if(response.isSuccessful){
                onSuccess(response.body()!!)
            } else {
                onError(BonsaiErrorResponse.convertFromErrorBody(response.errorBody()!!))
            }
        } catch (e: Exception) {
            onError(BonsaiErrorResponse.convertFromException(e))
        }
    }
}