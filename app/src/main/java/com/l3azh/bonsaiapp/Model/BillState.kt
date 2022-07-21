package com.l3azh.bonsaiapp.Model

import java.util.*

data class BillState(
    val uuidBill:String,
    val createDate:Date,
    val email:String,
    val listDetail:List<BillDetailState>
){
    fun getTotal():Double{
        var total = 0.0
        listDetail.forEach{
            total += (it.priceSold*it.quantity)
        }
        return total
    }
}

data class BillDetailState(
    val priceSold:Double,
    val quantity:Int,
    val tree: TreeState
)