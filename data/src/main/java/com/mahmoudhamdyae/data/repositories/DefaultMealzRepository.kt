package com.mahmoudhamdyae.data.repositories

import com.mahmoudhamdyae.data.local.MealzDatabase
import com.mahmoudhamdyae.data.remote.ApiService
import com.mahmoudhamdyae.data.remote.asDomainModel
import com.mahmoudhamdyae.domain.models.Meal
import com.mahmoudhamdyae.domain.repositories.MealzRepository

class DefaultMealzRepository(
    private val apiService: ApiService,
    private val database: MealzDatabase
): MealzRepository {

    override suspend fun getMealz(): List<Meal> {
//        withContext(Dispatchers.IO) {
//            val categories = apiService.getMealz()
//            database.dao().insertMealz(*categories.asDatabaseModel())
//        }
//        var listOfMealz: List<Meal> = listOf()
//        database.dao().getMealz().collect{
//            listOfMealz = it.asDomainModel()
//        }
//        return listOfMealz
        return apiService.getMealz().asDomainModel()
    }
}