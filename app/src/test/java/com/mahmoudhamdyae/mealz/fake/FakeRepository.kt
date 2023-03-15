package com.mahmoudhamdyae.mealz.fake

import com.mahmoudhamdyae.data.remote.asDomainModel
import com.mahmoudhamdyae.domain.models.Meal
import com.mahmoudhamdyae.domain.repositories.MealzRepository

class FakeRepository: MealzRepository {
    override suspend fun getMealz(): List<Meal> {
        return FakeDataSource.mealz.asDomainModel()
    }
}