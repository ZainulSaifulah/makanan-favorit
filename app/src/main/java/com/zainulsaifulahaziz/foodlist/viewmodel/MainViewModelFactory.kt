package com.zainulsaifulahaziz.foodlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zainulsaifulahaziz.foodlist.model.FoodRepository

class MainViewModelFactory(private val foodRepository: FoodRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(foodRepository) as T
    }
}