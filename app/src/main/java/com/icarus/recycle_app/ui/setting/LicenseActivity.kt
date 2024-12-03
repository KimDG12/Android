package com.icarus.recycle_app.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ExpandableListView
import com.icarus.recycle_app.R
import com.icarus.recycle_app.adapters.LicenseAdapter

class LicenseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_license)

        val expandableListView = findViewById<ExpandableListView>(R.id.expandableList)
        val adapter = LicenseAdapter(this, LicenseObject.TITLES, LicenseObject.ITEMS)

        expandableListView.setAdapter(adapter)


    }
}