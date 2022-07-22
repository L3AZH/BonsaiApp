package com.l3azh.bonsaiapp.Repository

import android.content.Context
import com.l3azh.bonsaiapp.Api.BonsaiApi
import com.l3azh.bonsaiapp.Api.Response.BonsaiErrorResponse
import com.l3azh.bonsaiapp.Api.Response.TotalBillByDayResponse
import com.l3azh.bonsaiapp.Api.Response.TreeQuantityOrderByDayResponse
import com.l3azh.bonsaiapp.Util.SharePrefUtils
import javax.inject.Inject

class StatisticRepository @Inject constructor(private val bonsaiApi: BonsaiApi) {

    suspend fun getTreeQuantityOrderByDay(
        context: Context, dayNumber: Int,
        onSuccess: (TreeQuantityOrderByDayResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try {
            val response =
                bonsaiApi.getTreeQuantityOrderByDay(
                    SharePrefUtils.getBearerToken(context),
                    dayNumber
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

    suspend fun getTotalAmountBillByDay(
        context: Context, dayNumber: Int,
        onSuccess: (TotalBillByDayResponse) -> Unit,
        onError: (BonsaiErrorResponse) -> Unit
    ) {
        try {
            val response =
                bonsaiApi.getTotalBillAmountByDay(SharePrefUtils.getBearerToken(context), dayNumber)
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