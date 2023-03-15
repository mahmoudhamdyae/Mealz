package com.mahmoudhamdyae.domain.repositories

import com.mahmoudhamdyae.domain.models.Meal
import kotlinx.coroutines.flow.Flow

interface MealzRepository {

    suspend fun getMealz(): Flow<List<Meal>>?
}