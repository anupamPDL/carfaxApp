package com.anupam.carfaxapp.data.entity.entity

import com.anupam.carfaxapp.data.entity.entity.FirstPhoto
import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("baseUrl") val baseUrl : String,
    @SerializedName("firstPhoto") val firstPhoto : FirstPhoto
) {
}