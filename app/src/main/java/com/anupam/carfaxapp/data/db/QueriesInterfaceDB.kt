package com.anupam.carfaxapp.data.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anupam.carfaxapp.data.entity.Listings

interface QueriesInterfaceDB {

    fun  getAllCars(): List<Listings>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(carList: List<Listings>)


    @Query("DELETE from listings_car")
    fun deleteAllData()
}