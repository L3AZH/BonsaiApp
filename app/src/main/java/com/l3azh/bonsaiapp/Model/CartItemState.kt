package com.l3azh.bonsaiapp.Model

import android.graphics.Bitmap

data class CartItemState(
    val uuid: String,
    val name: String,
    val description: String,
    val price: Double,
    val picture: Bitmap?,
    val type: TreeTypeState,
    var quantity:Int
)