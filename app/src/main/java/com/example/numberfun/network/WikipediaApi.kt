package com.example.numberfun.network

import retrofit2.http.GET
import retrofit2.http.Path

data class WikipediaSummary(
    val title: String,
    val extract: String
)

interface WikipediaApi {

    @GET("page/summary/{title}")
    suspend fun getSummary(
        @Path("title") title: String
    ): WikipediaSummary
}