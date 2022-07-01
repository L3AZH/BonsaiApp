package com.l3azh.bonsaiapp.Api.Response

import com.google.gson.Gson
import okhttp3.ResponseBody
import java.lang.Exception
import java.util.*

class BonsaiErrorResponse(
    val code:Int,
    val flag:Boolean,
    val errorMessage:String,
    val timeStamp: Date
) {
    companion object {
        fun convertFromErrorBody(errorBody: ResponseBody):BonsaiErrorResponse{
            return Gson().fromJson(errorBody.string(), BonsaiErrorResponse::class.java)
        }
        fun convertFromException(exception: Exception):BonsaiErrorResponse{
            return BonsaiErrorResponse(500, false, exception.message!!, Date())
        }
    }
}