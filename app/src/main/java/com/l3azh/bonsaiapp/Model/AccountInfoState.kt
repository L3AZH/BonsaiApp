package com.l3azh.bonsaiapp.Model

import android.graphics.Bitmap

data class AccountInfoState(
    var email:String,
    var firstName:String,
    var lastName:String,
    var phoneNumber:String,
    var role:String,
    var avatar:Bitmap?
) {
}