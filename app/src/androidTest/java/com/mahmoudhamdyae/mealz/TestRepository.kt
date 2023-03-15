package com.mahmoudhamdyae.mealz

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mahmoudhamdyae.data.local.MealzDao
import com.mahmoudhamdyae.data.local.MealzDatabase
import com.mahmoudhamdyae.data.remote.asDomainModel
import com.mahmoudhamdyae.data.repositories.DefaultMealzRepository
import com.mahmoudhamdyae.domain.models.Meal
import com.mahmoudhamdyae.domain.repositories.MealzRepository
import com.mahmoudhamdyae.mealz.fake.FakeApiService
import com.mahmoudhamdyae.mealz.fake.FakeDataSource
import com.mahmoudhamdyae.mealz.rules.TestDispatcherRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestRepository {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var dao: MealzDao
    private lateinit var database: MealzDatabase

    private lateinit var apiService: FakeApiService
    private lateinit var repository: MealzRepository

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MealzDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.dao()

        apiService = FakeApiService()
        repository = DefaultMealzRepository(
            apiService = apiService,
            database = database
        )
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun repository_getMealz_verifyMealzList() = runBlocking {
        val expected: List<Meal> = FakeDataSource.mealz.asDomainModel()
        val value: List<Meal> = repository.getMealz()?.first()!!

        assertEquals(expected, value)
    }
}