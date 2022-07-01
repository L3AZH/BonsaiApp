package com.l3azh.bonsaiapp.Util

import android.content.Context
import android.util.Base64
import com.l3azh.bonsaiapp.BuildConfig
import com.l3azh.bonsaiapp.Constant

class BonsaiAppUtils {
    companion object {
        fun getBasicAuth(): String {
            val credential = "${BuildConfig.BASIC_AUTH_USER}:${BuildConfig.BASIC_AUTH_PASS}"
            val credentialByteArray = credential.encodeToByteArray()
            val dataEncode: String =
                Base64.encodeToString(credentialByteArray, 0, credentialByteArray.size, Base64.NO_WRAP)
            return "Basic $dataEncode"
        }

        fun saveTokenToPref(context:Context, token:String){
            val pref = context.getSharedPreferences(Constant.SHARE_PREF_NAME, Context.MODE_PRIVATE)
            val editer = pref.edit()
            editer.putString(Constant.TOKEN_FIELD, token)
            editer.apply()
        }
    }
}