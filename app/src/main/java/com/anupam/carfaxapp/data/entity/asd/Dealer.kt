package com.anupam.carfaxapp.data.entity.entity

import com.google.gson.annotations.SerializedName

data class Dealer(
    @SerializedName("address") val address : String,
    @SerializedName("phone") val phone : String,
    @SerializedName("state") val state : String
) {
}