package com.icarus.recycle_app.ui.search.image

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icarus.recycle_app.dto.Image
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.utils.CameraHelper
import com.icarus.recycle_app.utils.ServerConnectHelper

class SearchImageViewModel : ViewModel() {

    lateinit var cameraHelper: CameraHelper

    var isClickedTextInfo = MutableLiveData(false)
    var isCameraOpened = MutableLiveData<Boolean>(false)
    var imageResultUri = MutableLiveData<Uri>()
    val uploadStatus: MutableLiveData<Boolean> = MutableLiveData()
    var imageByteArray: ByteArray = byteArrayOf()
    val navigateToNextActivity = MutableLiveData(false)


    var trashItems = MutableLiveData<List<Trash>>()


    fun toggleIsClickedTextInfo() {
        val currentValue = isClickedTextInfo.value ?: false
        isClickedTextInfo.value = !currentValue
    }

}