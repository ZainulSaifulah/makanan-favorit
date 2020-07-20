package com.zainulsaifulahaziz.foodlist.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainulsaifulahaziz.foodlist.R
import com.zainulsaifulahaziz.foodlist.adapter.FoodAdapter
import com.zainulsaifulahaziz.foodlist.model.FoodRepository
import com.zainulsaifulahaziz.foodlist.model.api.FoodResponse
import com.zainulsaifulahaziz.foodlist.viewmodel.MainViewModel
import com.zainulsaifulahaziz.foodlist.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_international.*

class InternationalFragment : Fragment() {
    private lateinit var mainViewModel : MainViewModel
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var intent : Intent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_international, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = MainViewModelFactory(FoodRepository(context).instance)
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java).apply {
            getInternationalFoods()
            internationalFoods.observe(viewLifecycleOwner,
                Observer<FoodResponse> { t ->
                    if (t != null) {
                        Log.e(TraditionalFragment::class.java.name, "onChanged: " + t.foods.size )
                        foodAdapter = FoodAdapter(t.foods) { food ->
                            intent = Intent(context, DetailActivity::class.java).apply {
                                putExtra("FOOD", food)
                            }
                            startActivity(intent)
                        }
                        rv_international.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = foodAdapter
                        }
                    }
                })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            InternationalFragment().apply { }
    }
}