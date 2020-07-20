package com.zainulsaifulahaziz.foodlist.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.zainulsaifulahaziz.foodlist.model.api.Food


@Dao
interface FoodDao {
    @Query("SELECT * FROM foods")
    fun getFoods() : LiveData<List<Food>>

    @Insert(onConflict = REPLACE)
    fun insertFood(food : Food)

    @Delete
    fun deleteFood(food : Food)

    @Query("SELECT * FROM foods WHERE id = :id")
    fun getFood(id : Int) : LiveData<Food>
}