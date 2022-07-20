package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

data class CreateBillResponseData(
    @SerializedName(value = "message")
    val message: String
)

data class CreateBillResponse(
    @SerializedName(value = "data")
    val data: CreateBillResponseData
) : BonsaiResponse(-1, false)