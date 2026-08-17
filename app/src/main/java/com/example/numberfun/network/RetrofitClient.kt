package com.example.numberfun.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

object RetrofitClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.mathjs.org/")
        .addConverterFactory(
            ScalarsConverterFactory.create()
        )
        .build()

    val wikipediaApi: WikipediaApi =
        retrofit.create<WikipediaApi>()
}