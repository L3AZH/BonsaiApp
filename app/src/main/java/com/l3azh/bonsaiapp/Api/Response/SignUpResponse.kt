package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SignUpResponseData(
    @SerializedName(value = "message")
    val message: String
)

data class SignUpResponse(
    @SerializedName(value = "data")
    val data:SignUpResponseData
):BonsaiResponse(-1, false), Serializable