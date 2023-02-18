package com.example.newsfetcher.feature.bookmarks.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.NewsMoreFragment
import com.example.newsfetcher.feature.mainscreen.domain.ArticleModel
import com.example.newsfetcher.feature.mainscreen.ui.ArticlesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment:Fragment(R.layout.fragment_bookmarks) {

    private val viewModel: BookmarksScreenViewModel by viewModel()

    private val rvBookmarkedArticles: RecyclerView by lazy { requireActivity().findViewById(R.id.rvBookmarkedArticles) }
    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter( {},
            { index ->
                viewModel.processUIEvent(UiEvent.OnBookmarkedArticleIconClicked(index))
            }
        )
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, ::render)

        rvBookmarkedArticles.adapter = adapter
    }




    private fun render(viewState: ViewState) {
        adapter.setData(viewState.bookmarksArticle)
    }

}




