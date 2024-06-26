package com.example.fetchapp.repository

import com.example.fetchapp.data_models.ItemData
import com.example.fetchapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): DataRepository {

    override suspend fun getNetworkData(): List<ItemData>? {
        return withContext(Dispatchers.IO) {
            val result = apiService.getFetchData()

            if (result.isSuccessful) {
                val data = result.body()
                data?.let {
                    // filter out null data
                    val filteredData = it.filter { item -> !item.name.isNullOrBlank() }
                    return@withContext filteredData
                }
            } else {
                null
            }
        }
    }

}