package com.example.tangoapp.ui.news

import android.os.Bundle
import android.view.View
import com.example.tangoapp.R
import com.example.tangoapp.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.fragment_news

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}