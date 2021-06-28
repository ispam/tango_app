package com.example.tangoapp.presentation

import androidx.lifecycle.ViewModel
import com.example.tangoapp.domain.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject constructor(private val getArticlesUseCase: GetArticlesUseCase): ViewModel() {


}