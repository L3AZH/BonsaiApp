package com.l3azh.bonsaiapp.Model

import android.graphics.Bitmap

class TreeState(
    val uuid:String,
    val name:String,
    val description:String,
    val price:Double,
    val picture:Bitmap,
    val type:TreeTypeState
) {
}