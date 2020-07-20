package com.zainulsaifulahaziz.foodlist.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainulsaifulahaziz.foodlist.R
import com.zainulsaifulahaziz.foodlist.adapter.FoodAdapter
import com.zainulsaifulahaziz.foodlist.model.FoodRepository
import com.zainulsaifulahaziz.foodlist.model.api.Food
import com.zainulsaifulahaziz.foodlist.viewmodel.MainViewModel
import com.zainulsaifulahaziz.foodlist.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {
//    private lateinit var mainViewModel: MainViewModel
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var intent: Intent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = MainViewModelFactory(FoodRepository(context).instance)
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        mainViewModel.apply {
            getFavoriteFoods()
            favoriteFoods.observe(viewLifecycleOwner,
                Observer<List<Food>> { t ->
                    Log.e(FavoriteFragment::class.java.name, "onChanged: " + t!!.size)

                    if (t.isEmpty()){
                        tv_empty.visibility = View.VISIBLE
                        rv_favorite.visibility = View.GONE
                    }


                    foodAdapter = FoodAdapter(t) {
                        food ->
                        intent = Intent(context, DetailActivity::class.java).apply {
                            putExtra("FOOD", food)
                        }
                        startActivity(intent)
                    }

                    rv_favorite.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = foodAdapter
                    }
                })
        }
    }

    companion object {
        @JvmStatic lateinit var mainViewModel: MainViewModel

        @JvmStatic
        fun newInstance() =
            FavoriteFragment().apply { }

        @JvmStatic
        fun dataChanged() {
            mainViewModel.getFavoriteFoods()
        }
    }

//    init {
//        val factory = MainViewModelFactory(FoodRepository(context).instance)
//        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
//    }
}