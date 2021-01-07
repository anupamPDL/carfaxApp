package com.anupam.carfaxapp.data.db

import android.content.Context
import com.anupam.carfaxapp.data.entity.Listings

class DatabaseService(context: Context) : QueriesInterfaceDB {
    private val dao: CarListingDAO =
        CarListDatabase.getDatabase(context).carListDao()

    override fun getAllCars(): List<Listings> {

        return dao.getAllCarList()
    }

    override fun insertAll(carList: List<Listings>) {
        dao.insertAll(carList)
    }

    override fun deleteAllData() {
        dao.deleteAllData()
    }
}