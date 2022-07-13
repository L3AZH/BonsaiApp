package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

data class UpdateAccountInfoResponseData(
    @SerializedName(value = "message")
    val message:String
)

data class UpdateAccountInfoResponse(
    @SerializedName(value = "data")
    val data:UpdateAccountInfoResponseData
):BonsaiResponse(-1, false) {
}