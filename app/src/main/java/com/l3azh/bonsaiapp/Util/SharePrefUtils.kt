package com.l3azh.bonsaiapp.Util

import android.content.Context
import com.l3azh.bonsaiapp.Constant

class SharePrefUtils {
    companion object{
        fun saveTokenToPref(context: Context, token:String){
            val pref = context.getSharedPreferences(Constant.SHARE_PREF_NAME, Context.MODE_PRIVATE)
            val editer = pref.edit()
            editer.putString(Constant.TOKEN_FIELD, token)
            editer.apply()
        }

        fun getBearerToken(context: Context):String{
            val pref = context.getSharedPreferences(Constant.SHARE_PREF_NAME, Context.MODE_PRIVATE)
            return "Bearer ${pref.getString(Constant.TOKEN_FIELD, "")}"
        }

        fun saveEmailToPref(context: Context, email:String){
            val pref = context.getSharedPreferences(Constant.SHARE_PREF_NAME, Context.MODE_PRIVATE)
            val editer = pref.edit()
            editer.putString(Constant.EMAIL_FIELD, email)
            editer.apply()
        }

        fun saveRoleToPref(context: Context, role:String){
            val pref = context.getSharedPreferences(Constant.SHARE_PREF_NAME, Context.MODE_PRIVATE)
            val editer = pref.edit()
            editer.putString(Constant.ROLE_FIELD, role)
            editer.apply()
        }
    }
}