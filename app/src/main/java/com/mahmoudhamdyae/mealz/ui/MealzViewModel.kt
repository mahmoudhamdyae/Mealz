package com.mahmoudhamdyae.mealz.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudhamdyae.domain.usecases.GetMealz
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mahmoudhamdyae.domain.models.Result

@HiltViewModel
class MealzViewModel @Inject constructor(
    private val getMealz: GetMealz
): ViewModel() {

    var uiState by mutableStateOf(MealzUiState())
     private set

    init {
        getAllMealz()
    }

    private fun getAllMealz() {
        viewModelScope.launch {
            uiState = uiState.copy(
                mealz = listOf(),
                isLoading = true,
                error = null
            )
            when (val response = getMealz()) {
                is Result.Success -> {
                    uiState = uiState.copy(
                        mealz = response.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Result.Error -> {
                    uiState = uiState.copy(
                        mealz = listOf(),
                        isLoading = false,
                        error = response.message
                    )
                }
            }
        }
    }
}