package com.icarus.recycle_app.dto

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Trash(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    var name : String,

    @SerializedName("type")
    var type: String,

    @SerializedName("category")
    var category: String,

    @SerializedName("method")
    var method: String,

    @SerializedName("etc")
    var etc: String,

    @SerializedName("views")
    var views: Int,

    @SerializedName("date")
    var date: String,

    @SerializedName("image")
    var image: String,

    @SerializedName("recycle_able")
    var recycleAble: String,

    @SerializedName("tags")
    var tags: String,

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeString(category)
        parcel.writeString(method)
        parcel.writeString(etc)
        parcel.writeInt(views)
        parcel.writeString(date)
        parcel.writeString(image)
        parcel.writeString(recycleAble)
        parcel.writeString(tags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trash> {
        override fun createFromParcel(parcel: Parcel): Trash {
            return Trash(parcel)
        }

        override fun newArray(size: Int): Array<Trash?> {
            return arrayOfNulls(size)
        }
    }
}
