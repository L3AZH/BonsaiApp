package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

data class UpdateTreeResponseData(
    @SerializedName(value = "message")
    val message:String
)

data class UpdateTreeResponse(
    @SerializedName(value = "data")
    val data:UpdateTreeResponseData
):BonsaiResponse(-1, false) {
}