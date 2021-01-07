package com.anupam.carfaxapp.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FirstPhoto(
    @SerializedName("large")
    @ColumnInfo(name = "large") val large : String

) : Parcelable {
}