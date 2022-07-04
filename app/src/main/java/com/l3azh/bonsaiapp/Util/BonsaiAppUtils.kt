package com.l3azh.bonsaiapp.Util

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import com.l3azh.bonsaiapp.BuildConfig
import com.l3azh.bonsaiapp.Constant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

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

        @SuppressLint("SimpleDateFormat")
        @RequiresApi(Build.VERSION_CODES.O)
        fun getCurrentDateString(): String {
            val df = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            return df.format(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))
        }
    }
}