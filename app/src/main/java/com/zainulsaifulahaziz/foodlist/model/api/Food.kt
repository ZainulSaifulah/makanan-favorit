package com.zainulsaifulahaziz.foodlist.model.api

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "foods")
data class Food(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "rating") val rating: Int,
    @ColumnInfo(name = "sort_description") val sortDescription: String,
    @ColumnInfo(name = "status") val status: Int
) : Parcelable