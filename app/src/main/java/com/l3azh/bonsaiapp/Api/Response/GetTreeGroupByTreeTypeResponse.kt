package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName

data class GetTreeGroupByTreeTypeResponse(
    @SerializedName(value = "data")
    val data: GetAllTreeGroupByTreeTypeData
) : BonsaiResponse(-1, false)