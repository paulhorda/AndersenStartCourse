package com.example.homework2_network.data.network

import com.example.homework2_network.data.model.ListResponse
import com.example.homework2_network.util.Constants.Companion.LISTS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET(LISTS_ENDPOINT)
    suspend fun getListData(): Response<List<ListResponse>>
}