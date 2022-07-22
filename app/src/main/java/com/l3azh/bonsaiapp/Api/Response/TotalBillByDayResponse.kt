package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName
import java.util.*

data class TotalBillByDayResponseData(
    @SerializedName(value = "totalBillTime")
    val totalBillTime: Date,
    @SerializedName(value = "totalBill")
    val totalBill: Double
)

data class TotalBillByDayResponse(
    @SerializedName(value = "data")
    val data: List<TotalBillByDayResponseData>
)