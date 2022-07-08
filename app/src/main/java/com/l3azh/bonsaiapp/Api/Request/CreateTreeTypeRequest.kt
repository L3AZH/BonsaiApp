package com.l3azh.bonsaiapp.Api.Request

import com.google.gson.annotations.SerializedName

data class CreateTreeTypeRequest(
    @SerializedName(value = "name")
    val name:String,
    @SerializedName(value = "description")
    val description:String,
) {
}