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

                    //sort the data by listId and if they're the same id, sort by name
                    val sortedData = filteredData.sortedWith(compareBy({ it.listId }, { it.name }))

                    return@withContext sortedData
                }
            } else {
                null
            }
        }
    }

}