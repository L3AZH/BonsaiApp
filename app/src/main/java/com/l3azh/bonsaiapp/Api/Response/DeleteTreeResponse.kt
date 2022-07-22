package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName


data class DeleteTreeResponseData(
    @SerializedName(value = "message")
    val message:String
)

data class DeleteTreeResponse(
    @SerializedName(value = "data")
    val data:DeleteTreeResponseData
)