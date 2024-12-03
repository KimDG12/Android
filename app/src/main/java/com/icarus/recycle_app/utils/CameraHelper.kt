package com.icarus.recycle_app.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CameraHelper(private val activity: Activity) {

    val REQUEST_IMAGE_CAPTURE = 1

    private var photoUri: Uri? = null

    @Throws(IOException::class)
    fun dispatchTakePictureIntent(): Intent? {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (takePictureIntent.resolveActivity(activity.packageManager) != null) {
            val photoFile = createImageFile()

            photoFile?.also {
                photoUri = FileProvider.getUriForFile(
                    activity,
                    "com.icarus.recycle_app.fileprovider",
                    it
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                return takePictureIntent
            }
        }
        return null
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpeg",
            storageDir
        )
    }

    fun getPhotoUri(): Uri? {
        return photoUri
    }
}