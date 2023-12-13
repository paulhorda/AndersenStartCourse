package com.example.homework2_network.data.sevice

import com.example.homework2_network.data.model.ListResponse
import com.example.homework2_network.data.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiService @Inject constructor(
    private val listApi: ApiInterface
) {
    suspend fun getList(): List<ListResponse> {
        return withContext(Dispatchers.IO) {
            val lists = listApi.getListData()
            lists.body() ?: emptyList()
        }
    }
}