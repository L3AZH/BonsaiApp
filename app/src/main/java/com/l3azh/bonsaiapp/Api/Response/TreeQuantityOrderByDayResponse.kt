package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

data class TreeQuantityOrderByDayResponseData(
    @SerializedName(value = "uuidTree")
    val uuidTree: String,
    @SerializedName(value = "nameTree")
    val nameTree: String,
    @SerializedName(value = "quantity")
    val quantity: Int
)

data class TreeQuantityOrderByDayResponse(
    @SerializedName(value = "data")
    val data: List<TreeQuantityOrderByDayResponseData>
)