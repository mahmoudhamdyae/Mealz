package com.mahmoudhamdyae.data.remote

import com.mahmoudhamdyae.data.local.LocalMeal
import com.mahmoudhamdyae.domain.models.Meal

data class RemoteCategoryResponse(
    val categories: List<RemoteMeal>
)

fun RemoteCategoryResponse.asDatabaseModel(): Array<LocalMeal> {
    return categories.map {
        LocalMeal(
            idCategory = it.idCategory,
            strCategory = it.strCategory,
            strCategoryDescription = it.strCategoryDescription,
            strCategoryThumb = it.strCategoryThumb
        )
    }.toTypedArray()
}

fun RemoteCategoryResponse.asDomainModel(): List<Meal> {
    return categories.map {
        Meal(
            idCategory = it.idCategory,
            strCategory = it.strCategory,
            strCategoryDescription = it.strCategoryDescription,
            strCategoryThumb = it.strCategoryThumb
        )
    }
}