package com.mahmoudhamdyae.mealz.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudhamdyae.domain.models.Result
import com.mahmoudhamdyae.domain.usecases.GetMealz
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealzViewModel @Inject constructor(
    private val getMealz: GetMealz
): ViewModel() {

    var state by mutableStateOf(MealzUiState())
     private set

    init {
        getAllMealz()
    }

    private fun getAllMealz() {
        viewModelScope.launch {
            state = state.copy(
                mealz = null,
                isLoading = true,
                error = null
            )
            when (val response = getMealz()) {
                is Result.Success -> {
                    state = state.copy(
                        mealz = response.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Result.Error -> {
                    state = state.copy(
                        mealz = null,
                        isLoading = false,
                        error = response.message
                    )
                }
            }
        }
    }
}