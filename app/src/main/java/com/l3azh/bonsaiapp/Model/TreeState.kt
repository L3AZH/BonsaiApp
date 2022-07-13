package com.l3azh.bonsaiapp.Model

import android.graphics.Bitmap
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils

class TreeState(
    val uuid: String,
    val name: String,
    val description: String,
    val price: Double,
    val picture: Bitmap,
    val type: TreeTypeState
) {
    fun convertForNavigation(): TreeStateForNavigation {
        return TreeStateForNavigation(
            uuid,
            name,
            description,
            price,
            BonsaiAppUtils.convertBitmapToStringData(picture),
            type
        )
    }
}

class TreeStateForNavigation(
    val uuid: String,
    val name: String,
    val description: String,
    val price: Double,
    val picture: String,
    val type: TreeTypeState
) {
    fun convertToNormalState(): TreeState {
        return TreeState(
            uuid,
            name,
            description,
            price,
            BonsaiAppUtils.getBitmapFromStringData(picture),
            type
        )
    }
}

