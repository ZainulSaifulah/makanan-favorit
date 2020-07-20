package com.zainulsaifulahaziz.foodlist.model.db

import android.content.Context
import androidx.room.*
import com.zainulsaifulahaziz.foodlist.model.api.Food

@Database(entities = [Food::class], version = 1)
abstract class FoodDatabase () : RoomDatabase() {
    abstract fun foodDao() : FoodDao

    companion object {
        private lateinit var INSTANCE: FoodDatabase
        fun getInstance(context: Context?): FoodDatabase {
            synchronized(FoodDatabase::class) {
                if (context != null) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        FoodDatabase::class.java, "food_db"
                    )
                        .build()
                }
            }
            return INSTANCE
        }

    }
}