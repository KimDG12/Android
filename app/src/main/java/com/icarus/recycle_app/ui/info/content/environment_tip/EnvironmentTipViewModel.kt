package com.icarus.recycle_app.ui.info.content.environment_tip

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icarus.recycle_app.dto.EnvironmentTip

class EnvironmentTipViewModel : ViewModel() {

    var environmentTipList = MutableLiveData<List<EnvironmentTip>>()

    var isClickedPosition = MutableLiveData(0)
}