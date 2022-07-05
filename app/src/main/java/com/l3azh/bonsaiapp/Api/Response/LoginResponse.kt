package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

class AccountInfoLoginResponseData(
    @SerializedName(value = "email")
    val email:String,
    @SerializedName(value = "role")
    val role:String
)

class LoginResponseData(
    @SerializedName(value = "token")
    val token:String,
    @SerializedName(value = "accInfo")
    val accInfo:AccountInfoLoginResponseData
)

class LoginResponse(
    @SerializedName(value = "data")
    val data:LoginResponseData
):BonsaiResponse(-1, false)