package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SignUpResponse(
    @SerializedName(value = "message")
    val message:String
):BonsaiResponse(-1, false), Serializable