package com.example.tangoapp.data.repositories

import com.example.tangoapp.data.ApiService
import com.example.tangoapp.data.entities.News
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.CoroutineDispatcher

@FragmentScoped
class NewsRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val apiService: ApiService
): NewsRepository {

    override suspend fun getArticles(): List<News>? {
        return with(dispatcher) {

            val service = apiService.getArticles()
            val body = service.body()
            if (service.isSuccessful && body != null) {
                body
            } else {
                null
            }
        }
    }
}