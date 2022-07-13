package com.l3azh.bonsaiapp.Util

import android.content.Context
import com.l3azh.bonsaiapp.Constant

class SharePrefUtils {
    companion object{
        fun saveTokenToPref(context: Context, token:String){
            val pref = context.getSharedPreferences(Constant.SHARE_PREF_NAME, Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString(Constant.TOKEN_FIELD, token)
            editor.apply()
        }

        fun getBearerToken(context: Context):String{
            val pref = context.getSharedPreferences(Constant.SHARE_PREF_NAME, Context.MODE_PRIVATE)
            return "Bearer ${pref.getString(Constant.TOKEN_FIELD, "")}"
        }

        fun saveEmailToPref(context: Context, email:String){
            val pref = context.getSharedPreferences(Constant.SHARE_PREF_NAME, Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString(Constant.EMAIL_FIELD, email)
            editor.apply()
        }

        fun getEmail(context: Context):String{
            val pref = context.getSharedPreferences(Constant.SHARE_PREF_NAME, Context.MODE_PRIVATE)
            return pref.getString(Constant.EMAIL_FIELD, "")!!
        }

        fun saveRoleToPref(context: Context, role:String){
            val pref = context.getSharedPreferences(Constant.SHARE_PREF_NAME, Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString(Constant.ROLE_FIELD, role)
            editor.apply()
        }

        fun getRole(context: Context):String{
            val pref = context.getSharedPreferences(Constant.SHARE_PREF_NAME, Context.MODE_PRIVATE)
            return pref.getString(Constant.ROLE_FIELD, "")!!
        }
    }
}