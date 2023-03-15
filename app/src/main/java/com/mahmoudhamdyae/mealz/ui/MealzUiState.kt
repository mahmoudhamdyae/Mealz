package com.mahmoudhamdyae.mealz.ui

import com.mahmoudhamdyae.domain.models.Meal

data class MealzUiState(
    val mealz: List<Meal> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
)