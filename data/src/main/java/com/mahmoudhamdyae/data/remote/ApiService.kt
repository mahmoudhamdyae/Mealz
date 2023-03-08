package com.mahmoudhamdyae.data.remote

import com.mahmoudhamdyae.domain.models.CategoryResponse
import retrofit2.http.GET

interface ApiService {

    @GET("categories.php")
    suspend fun getMealz(): CategoryResponse
}