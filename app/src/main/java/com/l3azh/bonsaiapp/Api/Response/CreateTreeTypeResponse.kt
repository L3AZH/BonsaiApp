package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

data class CreateTreeTypeRepsonseData(
    @SerializedName(value = "message")
    val message: String
)

data class CreateTreeTypeResponse(
    @SerializedName(value = "data")
    val data:CreateTreeTypeRepsonseData
):BonsaiResponse(-1, false) {
}