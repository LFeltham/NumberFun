package com.example.numberfun.network

import retrofit2.http.GET
import retrofit2.http.Query

interface WikipediaApi {

    @GET("v4/")
    suspend fun calculate(
        @Query("expr") expression: String
    ): String
}