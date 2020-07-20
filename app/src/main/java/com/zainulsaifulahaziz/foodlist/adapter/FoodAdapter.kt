package com.zainulsaifulahaziz.foodlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zainulsaifulahaziz.foodlist.R
import com.zainulsaifulahaziz.foodlist.model.api.Food
import com.zainulsaifulahaziz.foodlist.model.api.RetrofitService
import kotlinx.android.synthetic.main.food_item.view.*

class FoodAdapter (private val foods : List<Food>, private val adapterOnClick: (Food) -> Unit) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false))
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindFood(foods[position])
    }
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bindFood(food : Food){
            itemView.apply {
                tv_name.text = food.name
                tv_sort_description.text = food.sortDescription
                Glide.with(context).load(RetrofitService.BASE_URL + food.image).into(iv_food)
                cv_food.setOnClickListener { adapterOnClick(food) }
            }
        }
    }
}
