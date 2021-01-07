package com.anupam.carfaxapp.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.anupam.carfaxapp.data.entity.Listings

@Database(
        entities = [Listings::class],
        version = 1, exportSchema = false
)
abstract class CarListDatabase: RoomDatabase() {

    abstract fun carListDao(): CarListingDAO

    companion object {

        @Volatile
        private var INSTANCE: CarListDatabase? = null
        private const val DB_NAME = "carList.db"

        fun getDatabase(context: Context): CarListDatabase {

            if (INSTANCE == null) {
                synchronized(CarListDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, CarListDatabase::class.java, DB_NAME)
                                .allowMainThreadQueries()
                                .fallbackToDestructiveMigration()
                                .addCallback(object : Callback() {
                                    override fun onCreate(db: SupportSQLiteDatabase) {
                                        super.onCreate(db)
                                        Log.d("CarListDB", "populating with data...")
                                    }
                                }).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

}