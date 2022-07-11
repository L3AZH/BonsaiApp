package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

class UpdateTreeTypeResponseData(
    @SerializedName(value = "message")
    val message:String
)

class UpdateTreeTypeResponse(
    @SerializedName(value = "data")
    val data:UpdateTreeTypeResponseData
):BonsaiResponse(-1, false)