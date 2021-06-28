package com.example.tangoapp.ui.news.adapter

import com.example.tangoapp.data.entities.News
import com.example.tangoapp.ui.news.items.NewsFeaturedItem
import com.example.tangoapp.ui.news.items.NewsItem
import com.xwray.groupie.GroupieAdapter

class NewsAdapter(private val clickAction: (News) -> Unit): GroupieAdapter() {

    fun setInitialData(news: List<News>) {
        clear()
        news.forEachIndexed { index, news ->
            if (index == 0) {
                add(NewsFeaturedItem(news, clickAction))
            } else {
                add(NewsItem(news, clickAction))
            }
        }
    }
}