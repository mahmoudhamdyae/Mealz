package com.mahmoudhamdyae.data.remote

import retrofit2.http.GET

interface ApiService {

    @GET("categories.php")
    suspend fun getMealz(): RemoteCategoryResponse
}