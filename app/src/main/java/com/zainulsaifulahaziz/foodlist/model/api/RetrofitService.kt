package com.zainulsaifulahaziz.foodlist.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    public const val BASE_URL: String = "http://15.0.0.108:3000"

    fun create() : FoodEndpoint {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(FoodEndpoint::class.java)
    }
}