package com.anupam.carfaxapp.data.entity.entity

import com.google.gson.annotations.SerializedName

data class FirstPhoto(
    @SerializedName("large") val large : String,
    @SerializedName("medium") val medium : String,
    @SerializedName("small") val small : String
) {
}