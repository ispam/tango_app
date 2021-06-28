package com.example.tangoapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.tangoapp.common.live_data.Resource
import com.example.tangoapp.common.live_data.Event
import com.example.tangoapp.data.entities.News
import com.example.tangoapp.domain.GetArticlesUseCase
import com.example.tangoapp.presentation.NewsViewModel
import com.example.tangoapp.utils.CoroutineTestRule
import com.example.tangoapp.utils.InstantExecutorExtension
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.rules.TestRule
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NewsViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: NewsViewModel

    private val useCase: GetArticlesUseCase = mockk()
    private val stateObserver = mockk<Observer<Event<Resource<List<News>>>>>(relaxed = true)

    private val receivedUiStates = mutableListOf<Event<Resource<List<News>>>>()

    @BeforeEach
    fun beforeEach() {
        clearMocks(stateObserver)
        receivedUiStates.clear()
        viewModel = NewsViewModel(useCase)
    }

    @Nested
    @DisplayName("Given user is in the Articles Screen")
    inner class NewsFragment {

        @Test
        fun `when he arrives for the first time, and lost connection it should not see data`() = coroutinesTestRule.testDispatcher.runBlockingTest {

            coEvery { useCase.getArticles() } returns null

            observeViewModel(viewModel)
            viewModel.getArticles()

            assertEquals(
                listOf(
                    Event<Resource<List<News>>>(Resource.Loading),
                    Event<Resource<List<News>>>(Resource.Error(null)),
                ),
                receivedUiStates
            )
        }

        @Test
        fun `when he arrives for the first time, then it should display the articles`() = coroutinesTestRule.testDispatcher.runBlockingTest {
            val news1 = mockk<News>()
            val news2 = mockk<News>()
            val news3 = mockk<News>()
            val news4 = mockk<News>()
            val list = mutableListOf<News>().apply {
                add(news1)
                add(news2)
                add(news3)
                add(news4)
            }

            coEvery { useCase.getArticles() } returns list

            observeViewModel(viewModel)
            viewModel.getArticles()

            coVerify { useCase.getArticles() }
            assertEquals(
                listOf(
                    Event<Resource<List<News>>>(Resource.Loading),
                    Event<Resource<List<News>>>(Resource.Success(list))
                ),
                receivedUiStates
            )
        }
    }

    private fun observeViewModel(viewModel: NewsViewModel) {
        viewModel.getNewsLiveData().observeForever { dataState -> receivedUiStates.add(dataState) }
    }
}