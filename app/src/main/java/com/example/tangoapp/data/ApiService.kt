package com.example.tangoapp.data

import com.example.tangoapp.data.entities.News
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/articles")
    suspend fun getArticles(): Response<List<News>>
}