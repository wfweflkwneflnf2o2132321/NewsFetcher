package com.example.newsfetcher.feature

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.bookmarks.ui.BookmarksScreenViewModel
import com.example.newsfetcher.feature.mainscreen.domain.ArticleModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsMoreFragment(private val model: ArticleModel): Fragment(R.layout.fragment_news_more) {

    //private val viewModel: BookmarksScreenViewModel by viewModel()

    private val tvArticleDescription: TextView by lazy { requireActivity().findViewById( R.id.tvArticleDescription ) }
    private val tvArticleDate: TextView by lazy { requireActivity().findViewById( R.id.tvArticleDate ) }
    private val collapsingTolbar: CollapsingToolbarLayout by lazy { requireActivity().findViewById(R.id.collapsingToolbar) }
    private val main: TextView by lazy { requireActivity().findViewById(R.id.tvMain) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        collapsingTolbar.title = model.title
        tvArticleDescription.text = model.description
        tvArticleDate.text = model.publishedAt
        main.text= model.title
        getImageByUrl(model.urlToImage)
    }

    private fun getImageByUrl(url: String) {
        val previewImageView: AppCompatImageView = requireActivity().findViewById(R.id.previewImageView)
        Glide
            .with(this)
            .load(url)
            .placeholder(R.drawable.ic_baseline_article_24)
            .error(R.drawable.ic_baseline_error_outline_24)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .skipMemoryCache(true)
            .into(previewImageView)
    }

}