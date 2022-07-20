package com.l3azh.bonsaiapp.Api.Request

import com.google.gson.annotations.SerializedName

data class TreeOrder(
    @SerializedName(value = "uuidTree")
    val uuidTree:String,
    @SerializedName(value = "quantity")
    val quantity:Int,
    @SerializedName(value = "priceSold")
    val priceSold:Double
)

data class CreateBillRequest(
    @SerializedName(value = "listTree")
    val listTree:List<TreeOrder>
)