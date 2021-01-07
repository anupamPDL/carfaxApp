package com.anupam.carfaxapp.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClientInstance {
    private var retrofit: Retrofit? = null
    private const val BASE_URL =
        "https://carfax-for-consumers.firebaseio.com/assignment.json"

    val carList: Retrofit?
        get() {
            val httpClient = OkHttpClient.Builder()
                .callTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(2000, TimeUnit.SECONDS)
                .writeTimeout(3000, TimeUnit.SECONDS)
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            }
            return retrofit
        }
}