package com.anupam.carfaxapp.data.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarListingDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(carList: CarList)

    @Query("select * from listings_")
    fun getCarListUpdateIfAny(): LiveData<CarList>

}