package com.l3azh.bonsaiapp.Util

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import com.l3azh.bonsaiapp.BuildConfig
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class BonsaiAppUtils {
    companion object {
        fun getBasicAuth(): String {
            val credential = "${BuildConfig.BASIC_AUTH_USER}:${BuildConfig.BASIC_AUTH_PASS}"
            val credentialByteArray = credential.encodeToByteArray()
            val dataEncode: String =
                Base64.encodeToString(
                    credentialByteArray,
                    0,
                    credentialByteArray.size,
                    Base64.NO_WRAP
                )
            return "Basic $dataEncode"
        }

        @SuppressLint("SimpleDateFormat")
        @RequiresApi(Build.VERSION_CODES.O)
        fun getCurrentDateString(): String {
            val df = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            return df.format(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))
        }

        @JvmStatic
        fun convertBitmapToStringData(bitmap: Bitmap): String {
            var byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(byteArray, Base64.NO_WRAP)
        }

        @JvmStatic
        fun getBitmapFromStringData(bitmapString: String): Bitmap {
            val byteArray = Base64.decode(bitmapString, Base64.NO_WRAP)
            return BitmapFactory.decodeByteArray( byteArray, 0, byteArray.size)
        }

        @JvmStatic
        fun convertByteArrayToBitmap(dataByteArray: ByteArray):Bitmap?{
            return BitmapFactory.decodeByteArray(
                dataByteArray, 0, dataByteArray.size)
        }

        @JvmStatic
        fun convertBitmapToByteArray(dataBitmap: Bitmap):ByteArray?{
            var dataByteArrayResult = ByteArrayOutputStream()
            dataBitmap.compress(Bitmap.CompressFormat.JPEG, 90, dataByteArrayResult)
            return dataByteArrayResult.toByteArray()
        }
    }
}