package com.mahmoudhamdyae.mealz

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mahmoudhamdyae.data.local.LocalMeal
import com.mahmoudhamdyae.data.local.MealzDao
import com.mahmoudhamdyae.data.local.MealzDatabase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DaoTest {

    private lateinit var dao: MealzDao
    private lateinit var database: MealzDatabase

    @Before
    fun createDB() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MealzDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.dao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        database.close()
    }

    private var meal1 = LocalMeal("1", "title1", "description1", "thumb1")
    private var meal2 = LocalMeal("2", "title2", "description2", "thumb2")

    private suspend fun addOneMealToDb() {
        dao.insertMealz(meal1)
    }

    private suspend fun addTwoMealzToDb() {
        dao.insertMealz(meal1)
        dao.insertMealz(meal2)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @Throws(Exception::class)
    fun daoInsert_InsertMealIntoDb() = runTest {
        addOneMealToDb()

        var mealz = listOf<LocalMeal>()
        dao.getMealz().collect {
            mealz = it
        }
        assertEquals(mealz[0], meal1)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runTest {
        addTwoMealzToDb()
        var mealz = listOf<LocalMeal>()
        dao.getMealz().collect {
            mealz = it
        }
        Assert.assertEquals(mealz[0], meal1)
        Assert.assertEquals(mealz[1], meal2)
    }
}