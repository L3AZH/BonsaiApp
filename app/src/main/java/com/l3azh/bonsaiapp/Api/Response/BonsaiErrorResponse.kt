package com.l3azh.bonsaiapp.Api.Response

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.l3azh.bonsaiapp.Util.BonsaiAppUtils
import okhttp3.ResponseBody
import java.lang.Exception
import java.util.*

class BonsaiErrorResponse(
    @SerializedName(value = "code")
    val code:Int,
    @SerializedName(value = "flag")
    val flag:Boolean,
    @SerializedName(value = "errorMessage")
    val errorMessage:String,
    @SerializedName(value = "timeStamp")
    val timeStamp: String
) {
    companion object {
        fun convertFromErrorBody(errorBody: ResponseBody):BonsaiErrorResponse{
            return Gson().fromJson(errorBody.string(), BonsaiErrorResponse::class.java)
        }
        @RequiresApi(Build.VERSION_CODES.O)
        fun convertFromException(exception: Exception):BonsaiErrorResponse{
            return BonsaiErrorResponse(500, false, exception.message!!, BonsaiAppUtils.getCurrentDateString())
        }
    }
}