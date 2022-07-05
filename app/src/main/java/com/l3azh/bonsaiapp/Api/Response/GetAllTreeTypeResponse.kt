package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

class TreeTypeResponseData(
    @SerializedName(value = "uuidTreeType")
    val uuid:String,
    @SerializedName(value = "name")
    val name:String,
    @SerializedName(value = "description")
    val description:String,
)

class GetAllTreeTypeResponse(
    @SerializedName(value = "data")
    val data:List<TreeTypeResponseData>
):BonsaiResponse(-1, false) {
}