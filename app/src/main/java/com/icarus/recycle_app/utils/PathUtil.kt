package com.icarus.recycle_app.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import java.io.File

object PathUtil {

    fun getFileFromUri(uri: Uri, context: Context): File {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        val path = if (cursor == null) {
            uri.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            val result = cursor.getString(idx)
            cursor.close()
            result
        }
        return File(path!!)
    }

    fun uriToByteArray(context: Context, uri: Uri): ByteArray? {
        var byteArray: ByteArray? = null
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            byteArray = inputStream.readBytes()
        }
        return byteArray
    }
}