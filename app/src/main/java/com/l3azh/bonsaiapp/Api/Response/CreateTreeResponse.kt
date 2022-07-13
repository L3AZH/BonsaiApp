package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

data class CreateTreeResponseData(
    @SerializedName(value = "message")
    val message: String
)

data class CreateTreeResponse(
    @SerializedName(value = "data")
    val data:CreateTreeResponseData
): BonsaiResponse(-1, false) {
}