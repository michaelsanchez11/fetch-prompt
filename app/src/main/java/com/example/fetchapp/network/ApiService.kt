package com.example.fetchapp.network

import com.example.fetchapp.data_models.ItemData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("hiring.json")
    suspend fun getFetchData(): Response<List<ItemData>>
}