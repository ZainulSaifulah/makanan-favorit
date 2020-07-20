package com.zainulsaifulahaziz.foodlist.view

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

private val TAB_TITLES = arrayOf(
    "Makanan Tradisional",
    "Makanan Intenasional",
    "Makanan Favorit"
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position + 1) {
            1 -> return TraditionalFragment.newInstance()
            2 -> return InternationalFragment.newInstance()
            3 -> return FavoriteFragment.newInstance()
            else -> return TraditionalFragment.newInstance()
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        return 3
    }
}