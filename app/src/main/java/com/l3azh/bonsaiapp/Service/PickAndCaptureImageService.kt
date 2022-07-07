package com.l3azh.bonsaiapp.Service

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.io.IOException
import java.util.*

enum class TypeAction {
    ChoosePictureFromGallery,
    TakingPicture
}

typealias PickOrCaptureImageServiceCallBack = (Bitmap)->Unit

class PickAndCaptureImageService {
    companion object {
        private lateinit var activity: ComponentActivity
        var uriCapturePic: Uri? = null
        private lateinit var listScreenRegister:
                MutableMap<String, PickOrCaptureImageServiceCallBack>
        private lateinit var onResultLauncher: ActivityResultLauncher<Intent>
        private lateinit var onRequestPickPicLauncher: ActivityResultLauncher<String>
        private lateinit var onRequestCapturePicLauncher: ActivityResultLauncher<String>


        fun registerWithActivity(activity: ComponentActivity) {
            PickAndCaptureImageService.activity = activity
            listScreenRegister = mutableMapOf()
            initRequestAndResultLauncher(activity = activity)
        }

        fun registerTakePickFromScreen(
            nameScreen: String,
            callBack: PickOrCaptureImageServiceCallBack
        ) = listScreenRegister.put(nameScreen, callBack)

        fun launch(action: TypeAction) {
            when (action) {
                TypeAction.TakingPicture -> {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        initIntentPictureCapture(intent, activity)
                        onResultLauncher.launch(intent)
                    } else {
                        if (activity.checkSelfPermission(Manifest.permission.CAMERA) !=
                            PackageManager.PERMISSION_GRANTED
                        ) {
                            onRequestCapturePicLauncher.launch(Manifest.permission.CAMERA)
                        } else {
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            initIntentPictureCapture(intent, activity)
                            onResultLauncher.launch(intent)
                        }
                    }
                }
                TypeAction.ChoosePictureFromGallery -> {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                        val intent = Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        )
                        onResultLauncher.launch(intent)
                    } else {
                        if (activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) !=
                            PackageManager.PERMISSION_GRANTED
                        ) {
                            onRequestPickPicLauncher
                                .launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        } else {
                            val intent = Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                            )
                            onResultLauncher.launch(intent)
                        }
                    }
                }
            }
        }

        fun unRegisterOfScreen(nameScreen: String) {
            listScreenRegister.remove(nameScreen)
        }

        private fun initRequestAndResultLauncher(activity: ComponentActivity) {
            onResultLauncher = activity.registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                if (it.resultCode == ComponentActivity.RESULT_OK) {
                    try {
                        val uri: Uri?
                        if (it.data != null && it.data!!.data != null) {
                            uri = it.data!!.data
                        } else {
                            uri = uriCapturePic!!
                        }

                        val bitmapResult: Bitmap?
                        if (Build.VERSION.SDK_INT < 28) {
                            bitmapResult = MediaStore.Images.Media.getBitmap(activity.contentResolver, uri)
                        } else {
                            val source = ImageDecoder.createSource(activity.contentResolver, uri!!)
                            bitmapResult = ImageDecoder.decodeBitmap(source)
                        }
                        for (screenRegister in listScreenRegister.keys) {
                            listScreenRegister[screenRegister]?.let { callBack ->
                                callBack(bitmapResult!!)
                            }
                        }

                    } catch (exception: Exception) {
                        Log.getStackTraceString(exception)
                    }
                }
            }

            onRequestPickPicLauncher = activity.registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) {
                if (it) {
                    val intent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    onResultLauncher.launch(intent)
                }
            }

            onRequestCapturePicLauncher = activity.registerForActivityResult(
                ActivityResultContracts.RequestPermission(),
            ) {
                if (it) {
                    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    initIntentPictureCapture(takePictureIntent, activity)
                    onResultLauncher.launch(takePictureIntent)
                }
            }
        }

        private fun initIntentPictureCapture(
            takePictureIntent: Intent,
            activity: ComponentActivity
        ): Boolean {
            val fileName = "${Date().time}.jpg"
            val imageCollection: Uri?

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                imageCollection =
                    MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            } else imageCollection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            }
            return try {
                activity.contentResolver.insert(imageCollection, contentValues)?.also { uri ->
                    uriCapturePic = uri
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                } ?: throw IOException("Couldn't create MediaStore entry")
                true
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        }
    }
}