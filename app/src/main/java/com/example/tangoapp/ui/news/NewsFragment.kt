package com.example.tangoapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tangoapp.R
import com.example.tangoapp.common.live_data.TaskObserver
import com.example.tangoapp.common.live_data.observeTask
import com.example.tangoapp.data.entities.News
import com.example.tangoapp.databinding.FragmentNewsBinding
import com.example.tangoapp.presentation.NewsViewModel
import com.example.tangoapp.ui.news.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var dialog: AlertDialog

    private val newsViewModel by navGraphViewModels<NewsViewModel>(R.id.main_nav) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        newsViewModel.getArticles()
        observeTask(newsViewModel.getNewsLiveData(), getNewsTaskLiveData())
    }

    private fun getNewsTaskLiveData(): TaskObserver<List<News>> {
        return TaskObserver(
            onLoading = {
                dialog = AlertDialog.Builder(requireContext(), R.style.Custom_Dialog)
                    .setView(LayoutInflater.from(requireContext()).inflate(R.layout.view_loader, null))
                    .create()
                dialog.show()
            },
            onSuccess = { list ->
                list?.let {
                    dismissDialog()
                    newsAdapter.setInitialData(it)
                }
            },
            onError = {
                dismissDialog()
            })
    }

    private fun setupAdapter() {
        newsAdapter = NewsAdapter {

        }

        with(binding.newsRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    private fun dismissDialog() {
        if (this::dialog.isInitialized && dialog.isShowing) {
            dialog.dismiss()
        }
    }
}
