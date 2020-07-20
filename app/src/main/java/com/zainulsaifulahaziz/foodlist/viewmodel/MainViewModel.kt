package com.zainulsaifulahaziz.foodlist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zainulsaifulahaziz.foodlist.model.FoodRepository
import com.zainulsaifulahaziz.foodlist.model.api.Food
import com.zainulsaifulahaziz.foodlist.model.api.FoodResponse
import com.zainulsaifulahaziz.foodlist.view.FavoriteFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: FoodRepository) : ViewModel() {
    private val TAG = "MainViewModel"
    private val _traditionalFoods = MutableLiveData<FoodResponse>().apply { }
    val traditionalFoods: LiveData<FoodResponse> = _traditionalFoods

    private val _internationalFoods = MutableLiveData<FoodResponse>().apply { }
    val internationalFoods: LiveData<FoodResponse> = _internationalFoods

    lateinit var favoriteFoods: LiveData<List<Food>>
    lateinit var favoriteFood : LiveData<Food>

    fun getTraditionalFoods() {
        repository.getTraditionalFoods().enqueue(object : Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                _traditionalFoods.value = response.body()
            }

            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.message)
            }
        })
    }

    fun getInternationalFoods() {
        repository.getInternationalFoods().enqueue(object : Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                _internationalFoods.value = response.body()
            }

            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.message)
            }
        })
    }

    fun getFavoriteFoods() {
        favoriteFoods = repository.getFavoriteFoods()
    }

    fun insertFavoriteFood(food: Food) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavoriteFood(food)
        }
    }

    fun deleteFavoriteFood(food: Food) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavoriteFood(food)
        }
    }

    fun getFavoriteFood(id: Int) {
        favoriteFood = repository.getFavoriteFood(id)
    }
}