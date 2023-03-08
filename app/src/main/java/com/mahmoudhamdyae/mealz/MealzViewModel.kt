package com.mahmoudhamdyae.mealz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudhamdyae.domain.models.Category
import com.mahmoudhamdyae.domain.models.CategoryResponse
import com.mahmoudhamdyae.domain.usecases.GetMealz
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealzViewModel @Inject constructor(
    private val getMealz: GetMealz
): ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(listOf())
    val categories: StateFlow<List<Category>>
        get() = _categories

    init {
        getAllMealz()
    }

    private fun getAllMealz() {
        viewModelScope.launch {
            try {
                _categories.value = getMealz()
            } catch (e: Exception) {
                Log.e("MealzViewModel", e.message.toString())
            }
        }
    }
}