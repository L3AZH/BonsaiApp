package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName
import java.util.*

data class TreeTypeOfTreeOfBillDetailOfAllBillResponseData(
    @SerializedName(value = "uuidTreeType")
    val uuidTreeType: String,
    @SerializedName(value = "name")
    val name:String,
    @SerializedName(value = "description")
    val description:String
)

data class TreeOfBillDetailOfAllBillResponseData(
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
    val treeType:TreeTypeOfTreeOfBillDetailOfAllBillResponseData
)

data class BillDetailOfAllBillResponseData(
    @SerializedName(value = "treeDto")
    val treeDto:TreeOfBillDetailOfAllBillResponseData,
    @SerializedName(value = "priceSold")
    val priceSold:Double,
    @SerializedName(value = "quantity")
    val quantity:Int
)

data class AllBillResponseData(
    @SerializedName(value = "uuidBill")
    val uuidBill:String,
    @SerializedName(value = "createDate")
    val createDate: Date,
    @SerializedName(value = "email")
    val email:String,
    @SerializedName(value = "listBillDetail")
    val listBillDetail:List<BillDetailOfAllBillResponseData>
)

data class ListAllBillResponseData(
    @SerializedName(value = "listBill")
    val listBill:List<BillOfEmailResponseData>
)

data class AllBillResponse(
    @SerializedName(value = "data")
    val data:ListAllBillResponseData
):BonsaiResponse(-1, false)