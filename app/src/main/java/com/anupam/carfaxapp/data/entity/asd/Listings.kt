package com.anupam.carfaxapp.data.entity.entity

import com.anupam.carfaxapp.data.entity.entity.Dealer
import com.anupam.carfaxapp.data.entity.entity.Images
import com.google.gson.annotations.SerializedName

//@Entity(tableName = "listings_")
data class Listings(
        @SerializedName("bodytype") val bodytype : String,
        @SerializedName("engine") val engine : String,
        @SerializedName("drivetype") val drivetype : String,
        @SerializedName("exteriorColor") val exteriorColor : String,
        @SerializedName("interiorColor") val interiorColor : String,
        @SerializedName("transmission") val transmission : String,
        @SerializedName("mileage") val mileage : Int,
        @SerializedName("listPrice") val listPrice : Int,
        @SerializedName("trim") val trim : String,
        @SerializedName("model") val model : String,
        @SerializedName("make") val make : String,
        @SerializedName("year") val year : Int,

  //  @Embedded(prefix = "images_")
        @SerializedName("images") val images : Images,

  //  @Embedded(prefix = "dealer_")
        @SerializedName("dealer") val dealer : Dealer

    ) {
//
//    @PrimaryKey(autoGenerate = true)
//    var id: Int = 0
}