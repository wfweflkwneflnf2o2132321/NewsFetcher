package com.example.newsfetcher.feature.mainscreen.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsfetcher.feature.mainscreen.domain.ArticlesInteractor
import kotlinx.coroutines.launch

class MainScreenViewModel(private val interactor: ArticlesInteractor, private val bookmarksInteractor:BookmarksInteractor) : BaseViewModel<ViewState>() {

    override fun initialViewState() =
        ViewState(
            articlesList = emptyList(),
            articlesShown = emptyList(),
            isSearchEnable = false
        )

    init {
        processDataEvent(DataEvent.LoadArticles)
    }


    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when(event){
            is DataEvent.LoadArticles ->{
                viewModelScope.launch {
                    interactor.getArticles().fold(
                        onError = {
                             Log.e("ERROR",it.localizedMessage)

                        },
                        onSuccess = {
                            processDataEvent(DataEvent.OnLoadArticlesSucceed(it))
                        }
                    )
                }
                return null
            }
            is DataEvent.OnLoadArticlesSucceed ->{
                return previousState.copy(articlesList = event.articles, articlesShown = event.articles)
            }

            is UiEvent.OnArticleClicked ->{
//                viewModelScope.launch{
//                    bookmarksInteractor.create(previousState.articlesShown[event.index])
//                }
//                val prevList = previousState.articlesList.toMutableList()
//                prevList[event.index].mark = true
//                Log.d("MYTAG changed", "${prevList[event.index].mark}")
//                return previousState.copy(articlesList = prevList, articlesShown = prevList)
//            }
                previousState.articlesList[event.index].mark = true
                viewModelScope.launch {
                    bookmarksInteractor.create(previousState.articlesShown[event.index])
                }
                event.adapter.notifyDataSetChanged()
                return null
            }

            is UiEvent.OnSearchButtonClicked ->{
                return previousState.copy(
                    articlesShown = if (previousState.isSearchEnable)previousState.articlesList else previousState.articlesShown,
                    isSearchEnable = !previousState.isSearchEnable)
            }

            is UiEvent.OnSearchEdit -> {
                return previousState.copy(articlesShown = previousState.articlesList.filter {
                    it.title.contains(
                        event.text
                    )
                })
            }
            else -> return null
        }
    }

}