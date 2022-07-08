package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

data class CreateTreeResponse(
    @SerializedName(value = "message")
    val message:String
): BonsaiResponse(-1, false) {
}