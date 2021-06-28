package com.example.tangoapp.data

import com.example.tangoapp.data.entities.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("/v3/articles")
    suspend fun getArticles(): Response<List<News>>
}