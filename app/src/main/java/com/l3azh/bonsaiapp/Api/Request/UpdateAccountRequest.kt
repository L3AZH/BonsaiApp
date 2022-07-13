package com.l3azh.bonsaiapp.Api.Request

import com.google.gson.annotations.SerializedName

class UpdateAccountRequest(
    @SerializedName(value = "password")
    val password:String,
    @SerializedName(value = "firstName")
    val firstName:String,
    @SerializedName(value = "lastName")
    val lastName:String,
    @SerializedName(value = "phonenumber")
    val phoneNumber:String,
    @SerializedName(value = "avatar")
    val picture:String
) {

}