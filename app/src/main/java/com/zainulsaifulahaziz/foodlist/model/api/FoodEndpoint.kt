package com.zainulsaifulahaziz.foodlist.model.api

import retrofit2.Call
import retrofit2.http.GET

interface FoodEndpoint {
    @GET("/foods/traditional")
    fun getTraditionalFoods(): Call<FoodResponse>

    @GET("/foods/international")
    fun getInternationalFoods(): Call<FoodResponse>

}