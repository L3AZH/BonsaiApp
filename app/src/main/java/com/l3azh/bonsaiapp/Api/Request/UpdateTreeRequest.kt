package com.l3azh.bonsaiapp.Api.Request

import com.google.gson.annotations.SerializedName

data class UpdateTreeRequest(
    @SerializedName(value = "name")
    val name:String,
    @SerializedName(value = "description")
    val description:String,
    @SerializedName(value = "price")
    val price:Double,
    @SerializedName(value = "picture")
    val picture:String,
    @SerializedName(value = "uuidTreeType")
    val uuidTreeType:String
) {
}