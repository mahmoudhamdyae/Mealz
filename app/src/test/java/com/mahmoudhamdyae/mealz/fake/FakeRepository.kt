package com.mahmoudhamdyae.mealz.fake

import com.mahmoudhamdyae.data.remote.asDomainModel
import com.mahmoudhamdyae.domain.models.Meal
import com.mahmoudhamdyae.domain.repositories.MealzRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository: MealzRepository {
    override suspend fun getMealz(): Flow<List<Meal>> {
        val list = FakeDataSource.mealz.asDomainModel()
        return flow { emit(list) }
    }
}