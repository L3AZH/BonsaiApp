package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

data class GetTreeResponseByNameTreeTypeData(
    @SerializedName(value = "uuidTreeType")
    val uuidTreeType: String,
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "description")
    val description: String
)

data class GetTreeResponseByNameData(
    @SerializedName(value = "uuidTree")
    val uuid: String,
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "description")
    val description: String,
    @SerializedName(value = "price")
    val price: Double,
    @SerializedName(value = "picture")
    val picture: String,
    @SerializedName(value = "treeType")
    val treeType: GetAllTreeResponseTreeTypeData
)

data class GetTreeByNameResponse(
    @SerializedName(value = "data")
    val data: List<GetTreeResponseByNameData>
)