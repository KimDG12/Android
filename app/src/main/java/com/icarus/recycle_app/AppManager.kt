package com.icarus.recycle_app

import android.content.Context
import android.content.SharedPreferences
import android.text.BoringLayout
import androidx.appcompat.app.AppCompatActivity
import com.icarus.recycle_app.utils.DataManager
import java.lang.ref.WeakReference

object AppManager {
    private var uid: String? = null
    private var contextRef: WeakReference<Context>? = null
    private val sp: SharedPreferences?
        get() = contextRef?.get()?.getSharedPreferences(
            "UserPrefs",
            AppCompatActivity.MODE_PRIVATE
        )
    private var favorites: Map<Int, Boolean> = mapOf()

    fun init(context: Context) {
        contextRef = WeakReference(context)
        favorites = DataManager.stringToMap(sp?.getString("favorite",""))
    }

    fun getFavorites(): Map<Int, Boolean> {
        return DataManager.stringToMap(sp?.getString("favorite",""))
    }

    fun get8Favorites(): Map<Int, Boolean> {
        return DataManager.stringToTrueMap(sp?.getString("favorite",""),8)
    }

    fun setFavorites(key:Int,value:Boolean){
        val map = getFavorites().toMutableMap()
        map[key] = value
        sp?.edit()?.putString("favorite",DataManager.mapToString(map))?.apply()
    }

    fun startInit(){
        sp?.edit()?.apply {
            putBoolean("init", true)
            apply()
        }
    }

    fun getStartInit(): Boolean? {
        return sp?.getBoolean("init",false)
    }
    fun setUid(uid: String) {
        this.uid = uid
    }

    fun getUid(): String? {
        return uid
    }

    fun Context.dpToPx(dp: Int): Int {
        return (dp * this.resources.displayMetrics.density).toInt()
    }



}
