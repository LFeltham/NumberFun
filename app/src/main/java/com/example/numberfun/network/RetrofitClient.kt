package com.example.numberfun.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {

    val wikipediaApi: WikipediaApi by lazy {

        Retrofit.Builder()
            .baseUrl("https://api.mathjs.org/")
            .addConverterFactory(
                ScalarsConverterFactory.create()
            )
            .build()
            .create(WikipediaApi::class.java)
    }
}