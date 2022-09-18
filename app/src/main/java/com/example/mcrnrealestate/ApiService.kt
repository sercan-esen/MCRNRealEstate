package com.example.mcrnrealestate

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/realestate")
    suspend fun getRealEstates(
    ): ArrayList<DataItem>
}