package com.example.tangoapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tangoapp.common.live_data.MutableTaskLiveData
import com.example.tangoapp.common.live_data.TaskLiveData
import com.example.tangoapp.data.entities.News
import com.example.tangoapp.domain.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject constructor(private val getArticlesUseCase: GetArticlesUseCase): ViewModel() {

    val newsLiveData: MutableTaskLiveData<List<News>> = MutableTaskLiveData()

    fun getArticles() {
        viewModelScope.launch {
            newsLiveData.setLoading()

            val articles = getArticlesUseCase.getArticles()

            if (articles != null) {
                newsLiveData.setSuccess(articles)
            } else {
                newsLiveData.setError(null)
            }

        }
    }

    fun getNewsLiveData(): TaskLiveData<List<News>> = newsLiveData
}