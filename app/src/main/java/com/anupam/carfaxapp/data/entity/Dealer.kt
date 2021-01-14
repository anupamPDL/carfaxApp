package com.anupam.carfaxapp.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dealer(
        @SerializedName("address")
        @ColumnInfo(name = "address") val address : String,

        @SerializedName("phone")
        @ColumnInfo(name = "phone") val phone : String,

        @SerializedName("state")
        @ColumnInfo(name = "state") val state : String
): Parcelable {
}