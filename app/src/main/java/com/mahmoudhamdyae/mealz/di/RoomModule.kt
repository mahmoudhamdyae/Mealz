package com.mahmoudhamdyae.mealz.di

import android.app.Application
import androidx.room.Room
import com.mahmoudhamdyae.data.local.MealzDao
import com.mahmoudhamdyae.data.local.MealzDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): MealzDatabase {
        return Room.databaseBuilder(
            application,
            MealzDatabase::class.java,
            "mealz_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(database: MealzDatabase): MealzDao {
        return database.dao()
    }
}