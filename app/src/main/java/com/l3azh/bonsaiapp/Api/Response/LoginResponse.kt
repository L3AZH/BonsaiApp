package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

class LoginResponseData(
    @SerializedName(value = "token")
    val token:String
)

class LoginResponse(
    @SerializedName(value = "data")
    val data:LoginResponseData
):BonsaiResponse(-1, false)