package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

data class DeleteTreeTypeResponseData(
    @SerializedName(value = "message")
    val message:String
)

class DeleteTreeTypeResponse(
    @SerializedName(value = "data")
    val data:DeleteTreeTypeResponseData
)