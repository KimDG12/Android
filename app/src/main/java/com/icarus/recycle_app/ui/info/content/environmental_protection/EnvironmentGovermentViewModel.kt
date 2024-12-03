package com.icarus.recycle_app.ui.info.content.environmental_protection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icarus.recycle_app.dto.EnvironmentTip

class EnvironmentGovermentViewModel : ViewModel() {
    var environmentTipList = MutableLiveData<List<EnvironmentTip>>()

    var isClickedPosition = MutableLiveData(0)
}