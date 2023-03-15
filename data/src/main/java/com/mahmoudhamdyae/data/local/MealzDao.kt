package com.mahmoudhamdyae.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MealzDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealz(vararg localMealz: LocalMeal)

    @Query("SELECT * FROM meal_table")
    fun getMealz(): Flow<List<LocalMeal>>
}