package com.example.fetchapp.repository

import com.example.fetchapp.data_models.ItemData

interface DataRepository {
    suspend fun getNetworkData() : List<ItemData>?
}