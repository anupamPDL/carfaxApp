package com.anupam.carfaxapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {

    private var retrofit: Retrofit? = null
    private const val BASE_URL =
        "https://carfax-for-consumers.firebaseio.com/"

    val carList: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}