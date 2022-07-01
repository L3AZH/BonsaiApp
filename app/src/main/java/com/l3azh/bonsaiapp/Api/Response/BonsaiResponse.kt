package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class BonsaiResponse(
    @SerializedName(value = "code")
    val code:Int,
    @SerializedName(value = "flag")
    val flag:Boolean
)