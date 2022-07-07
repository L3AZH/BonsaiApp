package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

data class GetAllTreeResponseTreeTypeData(
    @SerializedName(value = "uuidTreeType")
    val uuidTreeType:String,
    @SerializedName(value = "name")
    val name:String,
    @SerializedName(value = "description")
    val description: String
)

data class TreeResponseData(
    @SerializedName(value = "uuidTree")
    val uuid:String,
    @SerializedName(value = "name")
    val name:String,
    @SerializedName(value = "description")
    val description:String,
    @SerializedName(value = "price")
    val price:Double,
    @SerializedName(value = "picture")
    val picture:String,
    @SerializedName(value = "treeType")
    val treeType:GetAllTreeResponseTreeTypeData
)

data class GetAllTreeResponse(
    @SerializedName(value = "data")
    val data:List<TreeResponseData>
):BonsaiResponse(-1, false)