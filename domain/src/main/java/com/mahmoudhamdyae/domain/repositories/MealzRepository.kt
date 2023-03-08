package com.mahmoudhamdyae.domain.repositories

import com.mahmoudhamdyae.domain.models.Category

interface MealzRepository {

    suspend fun getMealzFromRemote(): List<Category>
}