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
                _data.value = networkData
            }
        }
    }
}