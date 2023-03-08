package com.mahmoudhamdyae.mealz.ui

import com.mahmoudhamdyae.domain.models.Category

data class MealzUiState(
    val mealz: List<Category>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)