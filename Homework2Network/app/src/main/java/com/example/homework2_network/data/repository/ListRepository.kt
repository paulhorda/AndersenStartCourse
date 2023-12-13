package com.example.homework2_network.data.repository

import com.example.homework2_network.data.model.ListResponse
import com.example.homework2_network.data.sevice.ApiService
import com.example.homework2_network.util.Resource
import javax.inject.Inject

class ListRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getListResponse(): Resource<List<ListResponse>> {
        val response = try {
            apiService.getList()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }
}