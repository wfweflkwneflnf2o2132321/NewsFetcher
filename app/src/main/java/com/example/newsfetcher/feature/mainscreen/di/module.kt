package com.example.newsfetcher.feature.mainscreen.di

import com.example.newsfetcher.feature.mainscreen.data.ArticlesRemoteSource
import com.example.newsfetcher.feature.mainscreen.data.ArticlesRepository
import com.example.newsfetcher.feature.mainscreen.data.ArticlesRepositoryImpl
import com.example.newsfetcher.feature.mainscreen.data.NewsApi
import com.example.newsfetcher.feature.mainscreen.domain.ArticlesInteractor
import com.example.newsfetcher.feature.mainscreen.ui.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val mainScreenModel = module {
    single<NewsApi> {
        get <Retrofit>().create(NewsApi::class.java)
    }

    single<ArticlesRemoteSource> {
        ArticlesRemoteSource(api = get())
    }

    single <ArticlesRepository>{
        ArticlesRepositoryImpl(source = get())
    }

    single<ArticlesInteractor> {
        ArticlesInteractor(repository = get())
    }

    viewModel{
        MainScreenViewModel(interactor = get(), bookmarksInteractor = get())
    }






}