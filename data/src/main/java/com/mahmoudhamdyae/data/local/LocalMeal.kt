package com.mahmoudhamdyae.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_table")
data class LocalMeal (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val idCategory: String,
    @ColumnInfo(name = "title")
    val strCategory: String,
    @ColumnInfo(name = "description")
    val strCategoryDescription: String,
    @ColumnInfo(name = "thumb")
    val strCategoryThumb: String
)