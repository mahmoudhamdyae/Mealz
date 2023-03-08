package com.mahmoudhamdyae.mealz.di

import com.mahmoudhamdyae.domain.repositories.MealzRepository
import com.mahmoudhamdyae.domain.usecases.GetMealz
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideUseCase(repository: MealzRepository): GetMealz {
        return GetMealz(repository)
    }
}