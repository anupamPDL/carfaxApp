package com.anupam.carfaxapp.data.entity

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "listings_car", indices = [Index(value = ["images_car_baseUrl","dealer_caraddress"], unique = true)])
@Parcelize
data class Listings (
        @PrimaryKey(autoGenerate = true)
        var uuid: Int = 0,

        @SerializedName("id")
        @ColumnInfo(name = "id") val id: String,

        @SerializedName("bodytype")
        @ColumnInfo(name = "bodytype") val bodytype : String,

        @SerializedName("engine")
        @ColumnInfo(name = "engine") val engine : String,

        @SerializedName("drivetype")
        @ColumnInfo(name = "drivetype") val drivetype : String,

        @SerializedName("exteriorColor")
        @ColumnInfo(name = "exteriorColor") val exteriorColor : String,

        @SerializedName("interiorColor")
        @ColumnInfo(name = "interiorColor") val interiorColor : String,

        @SerializedName("transmission")
        @ColumnInfo(name = "transmission") val transmission : String,

        @SerializedName("mileage")
        @ColumnInfo(name = "mileage") val mileage : Int,

        @SerializedName("listPrice")
        @ColumnInfo(name = "listPrice") val listPrice : Int,

        @SerializedName("trim")
        @ColumnInfo(name = "trim") val trim : String,

        @SerializedName("model")
        @ColumnInfo(name = "model") val model : String,

        @SerializedName("make")
        @ColumnInfo(name = "make") val make : String,

        @SerializedName("year")
        @ColumnInfo(name = "year") val year : Int,

        @SerializedName("fuel")
        @ColumnInfo(name = "fuel") val fuel: String,

        @Embedded(prefix = "images_car_")
        @SerializedName("images") val images : Images?,

        @Embedded(prefix = "dealer_car")
        @SerializedName("dealer") val dealer : Dealer

): Parcelable {
}