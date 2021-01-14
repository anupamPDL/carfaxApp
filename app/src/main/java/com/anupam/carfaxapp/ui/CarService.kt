package com.anupam.carfaxapp.ui

import androidx.lifecycle.MutableLiveData
import com.anupam.carfaxapp.data.entity.CarList
import com.anupam.carfaxapp.retrofit.GetCarService
import com.anupam.carfaxapp.retrofit.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarService {

    fun fetchCars() : MutableLiveData<CarList> {
        val cars = MutableLiveData<CarList>()

        val service = RetrofitClientInstance.carList?.create(GetCarService::class.java)
        val call = service?.getAllCarList()

        call?.enqueue(object : Callback<CarList>{
            override fun onFailure(call: Call<CarList>, t: Throwable) {
                //todo
            }

            override fun onResponse(call: Call<CarList>, response: Response<CarList>) {
               cars.value = response.body()
            }
        })

        return cars
    }
}
