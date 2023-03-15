package com.mahmoudhamdyae.mealz.fake

import com.mahmoudhamdyae.data.remote.RemoteMeal
import com.mahmoudhamdyae.data.remote.RemoteCategoryResponse

object FakeDataSource {
    val mealz =
        RemoteCategoryResponse(
            listOf(
                RemoteMeal(
                    idCategory = "id1",
                    strCategory = "category1",
                    strCategoryDescription = "description1",
                    strCategoryThumb = "thumb1"
                ),
                RemoteMeal(
                    idCategory = "id2",
                    strCategory = "category2",
                    strCategoryDescription = "description2",
                    strCategoryThumb = "thumb2"
                )
            )
        )
}