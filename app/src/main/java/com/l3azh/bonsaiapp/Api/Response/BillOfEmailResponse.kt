package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName
import java.util.*

data class TreeTypeOfTreeOfBillDetailOfEmailResponseData(
    @SerializedName(value = "uuidTreeType")
    val uuidTreeType: String,
    @SerializedName(value = "name")
    val name:String,
    @SerializedName(value = "description")
    val description:String
)

data class TreeOfBillDetailOfEmailResponseData(
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
    val treeType:TreeTypeOfTreeOfBillDetailOfEmailResponseData
)

data class BillDetailOfEmailResponseData(
    @SerializedName(value = "treeDto")
    val treeDto:TreeOfBillDetailOfEmailResponseData,
    @SerializedName(value = "priceSold")
    val priceSold:Double,
    @SerializedName(value = "quantity")
    val quantity:Int
)

data class BillOfEmailResponseData(
    @SerializedName(value = "uuidBill")
    val uuidBill:String,
    @SerializedName(value = "createDate")
    val createDate:Date,
    @SerializedName(value = "email")
    val email:String,
    @SerializedName(value = "listBillDetail")
    val listBillDetail:List<BillDetailOfEmailResponseData>
)

data class ListBillOfEmailResponseData(
    @SerializedName(value = "listBill")
    val listBill:List<BillOfEmailResponseData>
)

data class BillOfEmailResponse(
    @SerializedName(value = "data")
    val data:ListBillOfEmailResponseData
):BonsaiResponse(-1, false)