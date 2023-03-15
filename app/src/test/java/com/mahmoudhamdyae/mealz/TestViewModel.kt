package com.mahmoudhamdyae.mealz

import com.mahmoudhamdyae.data.remote.asDomainModel
import com.mahmoudhamdyae.domain.usecases.GetMealz
import com.mahmoudhamdyae.mealz.fake.FakeDataSource
import com.mahmoudhamdyae.mealz.fake.FakeRepository
import com.mahmoudhamdyae.mealz.rules.TestDispatcherRule
import com.mahmoudhamdyae.mealz.ui.MealzViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestViewModel {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var getMealzUseCase: GetMealz
    private lateinit var fakeRepository: FakeRepository
    private lateinit var viewModel: MealzViewModel

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        getMealzUseCase = GetMealz(fakeRepository)
        viewModel = MealzViewModel(
            getMealz = getMealzUseCase
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun viewModel_getMealz_verifyMealzUiStateSuccess() =
        runTest {
            val expected = FakeDataSource.mealz.asDomainModel()
            val value = viewModel.uiState.mealz
            assertEquals(expected, value)
        }
}