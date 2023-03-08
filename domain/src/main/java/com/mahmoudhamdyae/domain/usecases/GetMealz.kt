package com.mahmoudhamdyae.domain.usecases

import com.mahmoudhamdyae.domain.models.Category
import com.mahmoudhamdyae.domain.models.Result
import com.mahmoudhamdyae.domain.repositories.MealzRepository

class GetMealz(
    private val repository: MealzRepository
) {
    suspend operator fun invoke(): Result<List<Category>> {
        return try {
            Result.Success(repository.getMealzFromRemote())
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }
}