package com.mahmoudhamdyae.mealz.di

import com.mahmoudhamdyae.data.local.MealzDatabase
import com.mahmoudhamdyae.data.remote.ApiService
import com.mahmoudhamdyae.data.repositories.DefaultMealzRepository
import com.mahmoudhamdyae.domain.repositories.MealzRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(apiService: ApiService, database: MealzDatabase): MealzRepository {
        return DefaultMealzRepository(apiService, database)
    }
}