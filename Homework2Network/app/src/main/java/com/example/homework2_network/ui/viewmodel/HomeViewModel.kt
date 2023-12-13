package com.example.homework2_network.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework2_network.data.model.ListResponse
import com.example.homework2_network.data.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val listRepository: ListRepository
) : ViewModel() {

    private val _data = MutableLiveData<List<ListResponse>>()
    val data: LiveData<List<ListResponse>>
        get() = _data

    fun updateData() {
        viewModelScope.launch {
            _data.value = listRepository.getListResponse().data ?: emptyList()
        }
    }
}