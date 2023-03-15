package com.mahmoudhamdyae.domain.repositories

import com.mahmoudhamdyae.domain.models.Meal

interface MealzRepository {

    suspend fun getMealz(): List<Meal>
}