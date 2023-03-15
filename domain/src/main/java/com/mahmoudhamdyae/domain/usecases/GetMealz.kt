package com.mahmoudhamdyae.domain.usecases

import com.mahmoudhamdyae.domain.models.Meal
import com.mahmoudhamdyae.domain.repositories.MealzRepository
import javax.inject.Inject
import com.mahmoudhamdyae.domain.models.Result

class GetMealz @Inject constructor(
    private val repository: MealzRepository
) {
    suspend operator fun invoke(): Result<List<Meal>> {
        return try {
            Result.Success(repository.getMealz())
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }
}