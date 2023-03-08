package com.mahmoudhamdyae.mealz.di

import com.mahmoudhamdyae.data.remote.ApiService
import com.mahmoudhamdyae.data.repositories.MealzRepositoryImpl
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
    fun provideRepository(apiService: ApiService): MealzRepository {
        return MealzRepositoryImpl(apiService)
    }
}