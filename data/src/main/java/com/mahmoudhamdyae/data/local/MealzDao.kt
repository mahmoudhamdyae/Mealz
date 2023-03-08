package com.mahmoudhamdyae.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface MealzDao {

    @Query("SELECT * FROM meal_table")
    suspend fun getMealz(): List<LocalMeal>
}