package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

data class GetTreeResponseDataTreeType(
    @SerializedName(value = "uuidTreeType")
    val uuidTreeType:String,
    @SerializedName(value = "name")
    val name:String,
    @SerializedName(value = "description")
    val description: String
)

data class GetTreeResponseData(
    @SerializedName(value = "uuidTree")
    val uuidTree:String,
    @SerializedName(value = "name")
    val name:String,
    @SerializedName(value = "description")
    val description:String,
    @SerializedName(value = "price")
    val price:Double,
    @SerializedName(value = "picture")
    val picture:String,
    @SerializedName(value = "treeType")
    val treeType:GetTreeResponseDataTreeType
)

data class GetTreeResponse(
    @SerializedName(value = "data")
    val data:GetTreeResponseData
):BonsaiResponse(-1, false)