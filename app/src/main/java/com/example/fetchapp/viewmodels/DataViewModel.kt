package com.example.fetchapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchapp.data_models.ItemData
import com.example.fetchapp.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val dataRepository: DataRepository
): ViewModel() {

    private val _data = MutableStateFlow<List<ItemData>?>(null)
    val data: StateFlow<List<ItemData>?> get() = _data

    fun getItemDataList() {
        viewModelScope.launch {
            val networkData = dataRepository.getNetworkData()

            if (networkData != null) {
                val filteredData = networkData.filter { !it.name.isNullOrBlank() }
                val sortedData = sortItemDataList(filteredData)
                _data.value = sortedData
            }
        }
    }

    private fun extractLastNumber(name: String): Int? {
        val words = name.split(" ")
        val lastWord = words.lastOrNull()
        return lastWord?.toIntOrNull()
    }

    /**
     * Thing to Note: We can identify that the name attribute is simply composed of
     * "Item " + it.id. Therefore we can change this comparison to simply
     * return itemList.sortedWith(compareBy({ it.listId }, { it.id }))
     * and remove the extractLastNumber(name: String) method.
     *
     * Per requirements, it said to compare based on name and to be fair names can change,
     * therefore I am keeping the following implementation as it stays true to the requirements
     * and prevents any data corruption by assuming id will always be the same as the Item name.
     */
    private fun sortItemDataList(itemList: List<ItemData>): List<ItemData> {
        return itemList.sortedWith(compareBy({ it.listId }, { extractLastNumber(it.name ?: "") ?: Int.MAX_VALUE }))
    }
}