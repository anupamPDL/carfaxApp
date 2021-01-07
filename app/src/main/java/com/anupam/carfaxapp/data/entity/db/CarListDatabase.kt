package com.anupam.carfaxapp.data.dto

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Listings::class],
    version = 1
)
abstract class CarListDatabase: RoomDatabase() {

    abstract fun carListDao(): CarListingDAO

    companion object {

       @Volatile private var instance: CarListDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            CarListDatabase::class.java, "carList.db")
                .build()
    }

}