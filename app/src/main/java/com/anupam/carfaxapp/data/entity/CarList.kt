package com.anupam.carfaxapp.data.entity

import com.google.gson.annotations.SerializedName

data class CarList(@SerializedName("listings") var carList: List<Listings>) {
}