package com.zainulsaifulahaziz.foodlist.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.zainulsaifulahaziz.foodlist.R
import com.zainulsaifulahaziz.foodlist.model.FoodRepository
import com.zainulsaifulahaziz.foodlist.model.api.Food
import com.zainulsaifulahaziz.foodlist.model.api.RetrofitService
import com.zainulsaifulahaziz.foodlist.viewmodel.MainViewModel
import com.zainulsaifulahaziz.foodlist.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.content_detail.tv_name


class DetailActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))

        val food = intent.getParcelableExtra<Food>("FOOD")

        val factory = MainViewModelFactory(FoodRepository(this).instance)
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java).apply {
            getFavoriteFood(food.id)
            favoriteFood.observe(this@DetailActivity, Observer <Food> {
                t ->
                if(food == t) {
                    isFavorite = true
                    iv_favorite.setImageResource(R.drawable.ic_favorite)
                }
            })
        }

        tv_name.text = food.name
        tv_description.text = food.description
        tv_country.text = food.country
        rb_rating.rating = food.rating.toFloat()
        Glide.with(baseContext).load(RetrofitService.BASE_URL + food.image).into(iv_picture)
        iv_favorite.setOnClickListener {
            if(isFavorite) {
                iv_favorite.setImageResource(R.drawable.ic_not_favorite)
                isFavorite = false
                mainViewModel.deleteFavoriteFood(food)
            }else {
                iv_favorite.setImageResource(R.drawable.ic_favorite)
                isFavorite = true
                mainViewModel.insertFavoriteFood(food)
            }
        }
    }
}