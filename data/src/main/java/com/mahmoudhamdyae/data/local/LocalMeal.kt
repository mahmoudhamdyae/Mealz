package com.mahmoudhamdyae.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mahmoudhamdyae.domain.models.Meal

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

fun List<LocalMeal>.asDomainModel(): List<Meal> {
    return map {
        Meal(
            idCategory = it.idCategory,
            strCategory = it.strCategory,
            strCategoryDescription = it.strCategoryDescription,
            strCategoryThumb = it.strCategoryThumb
        )
    }
}