package com.mahmoudhamdyae.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalMeal::class],
    version = 1,
    exportSchema = false
)
abstract class MealzDatabase : RoomDatabase() {
    abstract fun dao(): MealzDao
}