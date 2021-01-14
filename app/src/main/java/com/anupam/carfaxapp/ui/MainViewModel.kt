package com.anupam.carfaxapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anupam.carfaxapp.data.entity.CarList

class MainViewModel : ViewModel() {

    var cars: MutableLiveData<CarList> = MutableLiveData()
    var service: CarService = CarService()

    init {
        fetchCars()
    }

    private fun fetchCars() {
        cars = service.fetchCars()
    }

}