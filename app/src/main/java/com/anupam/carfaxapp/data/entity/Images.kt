package com.anupam.carfaxapp.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Images(
        @SerializedName("baseUrl")
        @ColumnInfo(name = "baseUrl") val baseUrl : String,

        @Embedded(prefix = "first_photo_")
        @SerializedName("firstPhoto") val firstPhoto : FirstPhoto?

): Parcelable {
}