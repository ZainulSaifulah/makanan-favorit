package com.zainulsaifulahaziz.foodlist.model

import android.content.Context
import androidx.lifecycle.LiveData
import com.zainulsaifulahaziz.foodlist.model.api.Food
import com.zainulsaifulahaziz.foodlist.model.api.FoodResponse
import com.zainulsaifulahaziz.foodlist.model.api.RetrofitService
import com.zainulsaifulahaziz.foodlist.model.db.FoodDatabase
import retrofit2.Call

class FoodRepository(context: Context?) {
    val instance by lazy { FoodRepository(context) }

    private val foodDatabase = FoodDatabase.getInstance(context)
    private val retrofitService = RetrofitService.create()

    fun getTraditionalFoods(): Call<FoodResponse> {
        return retrofitService.getTraditionalFoods()
    }

    fun getInternationalFoods(): Call<FoodResponse> {
        return retrofitService.getInternationalFoods()
    }

    fun getFavoriteFoods(): LiveData<List<Food>> {
        return foodDatabase.foodDao().getFoods()
    }

    fun insertFavoriteFood(food: Food) {
        return foodDatabase.foodDao().insertFood(food)
    }

    fun deleteFavoriteFood(food: Food){
        return foodDatabase.foodDao().deleteFood(food)
    }

    fun getFavoriteFood(id : Int) : LiveData<Food>{
        return foodDatabase.foodDao().getFood(id)
    }

}