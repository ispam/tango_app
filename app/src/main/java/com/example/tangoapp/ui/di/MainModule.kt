package com.example.tangoapp.ui.di

import com.example.tangoapp.data.ApiService
import com.example.tangoapp.data.repositories.NewsRepository
import com.example.tangoapp.data.repositories.NewsRepositoryImpl
import com.example.tangoapp.di.annotations.IoDispatcher
import com.example.tangoapp.domain.GetArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    fun provideGroupRepositoryImpl(@IoDispatcher
                                   dispatcher: CoroutineDispatcher,
                                   apiService: ApiService): NewsRepository = NewsRepositoryImpl(dispatcher, apiService)

    @Provides
    fun provideCreateGroupUseCase(newsRepository: NewsRepository): GetArticlesUseCase = GetArticlesUseCase(newsRepository)
}