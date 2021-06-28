package com.example.tangoapp.data.repositories

import com.example.tangoapp.data.entities.News

interface NewsRepository {

    suspend fun getArticles(): List<News>?
}