package com.mahmoudhamdyae.mealz.fake

import com.mahmoudhamdyae.data.remote.ApiService
import com.mahmoudhamdyae.data.remote.RemoteCategoryResponse

class FakeApiService: ApiService {

    override suspend fun getMealz(): RemoteCategoryResponse {
        return FakeDataSource.mealz
    }
}