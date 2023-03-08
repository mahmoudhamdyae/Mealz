package com.mahmoudhamdyae.data.repositories

import com.mahmoudhamdyae.data.remote.ApiService
import com.mahmoudhamdyae.domain.models.Category
import com.mahmoudhamdyae.domain.models.CategoryResponse
import com.mahmoudhamdyae.domain.repositories.MealzRepository

class MealzRepositoryImpl(
    private val apiService: ApiService
): MealzRepository {
    override suspend fun getMealzFromRemote(): List<Category> {
        val categories = apiService.getMealz().categories
        return categories
    }
}