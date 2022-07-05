package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

class InfoAccountResponseData(
    @SerializedName(value = "email")
    val email:String,
    @SerializedName(value = "firstName")
    val firstName:String,
    @SerializedName(value = "lastName")
    val lastName:String,
    @SerializedName(value = "phonenumber")
    val phoneNumber:String,
    @SerializedName(value = "role")
    val role:String,
    @SerializedName(value = "avatar")
    val avatar:String
)

class InfoAccountResponse(
    @SerializedName(value = "data")
    val data:InfoAccountResponseData
):BonsaiResponse(-1, false) {
}