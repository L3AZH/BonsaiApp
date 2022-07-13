package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

data class GetAllTreeGroupByTreeTypeTreeType(
    @SerializedName(value = "uuidTreeType")
    val uuidTreeType:String,
    @SerializedName(value = "name")
    val name:String,
    @SerializedName(value = "description")
    val description:String
)

data class GetAllTreeGroupByTreeTypeTree(
    @SerializedName(value = "uuidTree")
    val uuidTree:String,
    @SerializedName(value = "name")
    val name:String,
    @SerializedName(value = "description")
    val description: String,
    @SerializedName(value = "price")
    val price:Double,
    @SerializedName(value = "picture")
    val picture:String,
    @SerializedName(value = "treeType")
    val treeType:GetAllTreeGroupByTreeTypeTreeType
)

data class GetAllTreeGroupByTreeTypeData(
    @SerializedName(value = "treeTypeDto")
    val treeTypeDto:GetAllTreeGroupByTreeTypeTreeType,
    @SerializedName(value = "listTree")
    val listTree:List<GetAllTreeGroupByTreeTypeTree>
)

data class GetAllTreeGroupByTreeTypeResponse(
    @SerializedName(value = "data")
    val data:List<GetAllTreeGroupByTreeTypeData>
):BonsaiResponse(-1, false) {
}