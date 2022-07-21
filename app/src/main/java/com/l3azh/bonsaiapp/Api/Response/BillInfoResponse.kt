package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.annotations.SerializedName
import java.util.*

data class TreeTypeOfTreeOfBillDetailOfBillInfoResponseData(
    @SerializedName(value = "uuidTreeType")
    val uuidTreeType: String,
    @SerializedName(value = "name")
    val name:String,
    @SerializedName(value = "description")
    val description:String
)

data class TreeOfBillDetailOfBillInfoResponseData(
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
    val treeType:TreeTypeOfTreeOfBillDetailOfBillInfoResponseData
)

data class BillDetailOfBillInfoResponseData(
    @SerializedName(value = "treeDto")
    val treeDto:TreeOfBillDetailOfBillInfoResponseData,
    @SerializedName(value = "priceSold")
    val priceSold:Double,
    @SerializedName(value = "quantity")
    val quantity:Int
)

data class BillInfoResponseData(
    @SerializedName(value = "uuidBill")
    val uuidBill:String,
    @SerializedName(value = "createDate")
    val createDate: Date,
    @SerializedName(value = "email")
    val email:String,
    @SerializedName(value = "listBillDetail")
    val listBillDetail:List<BillDetailOfBillInfoResponseData>
)

data class BillObjectBillInfoResponseData(
    @SerializedName(value = "billInfo")
    val billInfo:BillInfoResponseData
)

data class BillInfoResponse(
    @SerializedName(value = "data")
    val data:BillObjectBillInfoResponseData
):BonsaiResponse(-1, false)