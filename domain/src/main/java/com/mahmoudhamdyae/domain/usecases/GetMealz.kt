package com.mahmoudhamdyae.domain.usecases

import com.mahmoudhamdyae.domain.repositories.MealzRepository

class GetMealz(
    private val repo: MealzRepository
) {
    suspend operator fun invoke() = repo.getMealzFromRemote()
}