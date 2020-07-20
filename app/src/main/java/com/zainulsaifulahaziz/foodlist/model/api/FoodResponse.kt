package com.zainulsaifulahaziz.foodlist.model.api

data class FoodResponse(
    val status: Int,
    val foods: List<Food>
)