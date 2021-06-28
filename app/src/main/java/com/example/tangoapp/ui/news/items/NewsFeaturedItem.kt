package com.example.tangoapp.ui.news.items

import android.view.View
import com.example.tangoapp.R
import com.example.tangoapp.data.entities.News
import com.example.tangoapp.databinding.NewsFeaturedItemViewBinding
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem

class NewsFeaturedItem(private val news: News, private val clickAction: (News) -> Unit): BindableItem<NewsFeaturedItemViewBinding>() {

    override fun bind(viewBinding: NewsFeaturedItemViewBinding, position: Int) {

        with(viewBinding) {
            Picasso.get().load(news.imageUrl).fit().centerCrop().into(featuredItemImage)
            featuredTitleText.text = news.title
            featuredSubtitleText.text = news.summary
        }
    }

    override fun initializeViewBinding(view: View): NewsFeaturedItemViewBinding {
        return NewsFeaturedItemViewBinding.bind(view)
    }

    override fun getLayout(): Int = R.layout.news_featured_item_view
}