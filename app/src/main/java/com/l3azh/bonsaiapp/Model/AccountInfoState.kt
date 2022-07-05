package com.l3azh.bonsaiapp.Model

import android.graphics.Bitmap

class AccountInfoState(
    val email:String,
    val firstName:String,
    val lastName:String,
    val phoneNumber:String,
    val role:String,
    val avatar:Bitmap?
) {
}