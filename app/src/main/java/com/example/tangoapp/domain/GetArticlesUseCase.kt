package com.example.tangoapp.domain

import com.example.tangoapp.data.entities.News
import com.example.tangoapp.data.repositories.NewsRepository
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class GetArticlesUseCase(
    private val newsRepository: NewsRepository
) {

    suspend fun getArticles(): List<News>? {
        return newsRepository.getArticles()
    }
}