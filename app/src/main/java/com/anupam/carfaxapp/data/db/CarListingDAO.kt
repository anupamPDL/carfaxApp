package com.anupam.carfaxapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anupam.carfaxapp.data.entity.Listings

@Dao
interface CarListingDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(carList: List<Listings>)

    @Query("select * from listings_car")
    fun getAllCarList(): List<Listings>

    @Query("delete from listings_car")
    fun deleteAllData()
}