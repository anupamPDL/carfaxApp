package com.anupam.carfaxapp.retrofit

import com.anupam.carfaxapp.data.entity.CarList
import retrofit2.Call
import retrofit2.http.GET

interface GetCarService {

    @GET("assignment.json")
    fun getAllCarList() : Call<CarList>
}