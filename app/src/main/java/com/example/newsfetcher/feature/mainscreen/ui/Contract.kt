package com.example.newsfetcher.feature.mainscreen.ui

import android.widget.EditText
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.mainscreen.domain.ArticleModel

data class ViewState(
    val isSearchEnable: Boolean,
    val articlesShown: List<ArticleModel>,
    val articlesList: List<ArticleModel>
)
sealed class UiEvent: Event{
    data class OnArticleClicked(val index: Int ): UiEvent()
   object OnSearchButtonClicked: UiEvent()
    data class OnSearchEdit(val text: String): UiEvent()
}



sealed class DataEvent : Event{
    object LoadArticles : DataEvent()
    data class OnLoadArticlesSucceed(val articles: List<ArticleModel>): DataEvent()

}