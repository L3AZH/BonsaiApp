package com.l3azh.bonsaiapp.Api.Request

import com.google.gson.annotations.SerializedName

data class UpdateTreeTypeRequest(
    @SerializedName(value = "name")
    val name:String,
    @SerializedName(value = "description")
    val description:String
) {
}