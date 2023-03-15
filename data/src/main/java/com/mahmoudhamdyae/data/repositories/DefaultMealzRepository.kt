package com.mahmoudhamdyae.data.repositories

import android.util.Log
import com.mahmoudhamdyae.data.local.MealzDatabase
import com.mahmoudhamdyae.data.local.asDomainModel
import com.mahmoudhamdyae.data.remote.ApiService
import com.mahmoudhamdyae.data.remote.asDatabaseModel
import com.mahmoudhamdyae.domain.models.Meal
import com.mahmoudhamdyae.domain.repositories.MealzRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DefaultMealzRepository(
    private val apiService: ApiService,
    private val database: MealzDatabase
): MealzRepository {

    override suspend fun getMealz(): Flow<List<Meal>>? {
        var listOfMealz: Flow<List<Meal>>? = null

        withContext(Dispatchers.IO) {
            try {
                val categories = apiService.getMealz()
                database.dao().insertMealz(*categories.asDatabaseModel())
            } catch (e: Exception) {
                Log.e("Default Repository", e.message.toString())
            }
            listOfMealz = database.dao().getMealz().map {
                it.asDomainModel()
            }
        }

        return listOfMealz
    }
}