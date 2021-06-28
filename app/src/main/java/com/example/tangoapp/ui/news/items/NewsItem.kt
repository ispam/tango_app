package com.example.tangoapp.ui.news.items

import android.view.View
import com.example.tangoapp.R
import com.example.tangoapp.data.entities.News
import com.example.tangoapp.databinding.NewsItemViewBinding
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem

class NewsItem(private val news: News, private val clickAction: (News) -> Unit): BindableItem<NewsItemViewBinding>() {

    override fun bind(viewBinding: NewsItemViewBinding, position: Int) {

        with(viewBinding) {
            Picasso.get().load(news.imageUrl).fit().centerCrop().into(newsItemImage)
            newsItemTitleText.text = news.title
            newsItemSubtitleText.text = news.summary
        }
    }

    override fun initializeViewBinding(view: View): NewsItemViewBinding {
        return NewsItemViewBinding.bind(view)
    }

    override fun getLayout(): Int = R.layout.news_item_view
}