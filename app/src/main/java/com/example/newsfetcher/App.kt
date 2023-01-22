package com.example.newsfetcher

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.newsfetcher.di.databaseModule
import com.example.newsfetcher.di.networkModule
import com.example.newsfetcher.feature.bookmarks.di.bookmarksModule
import com.example.newsfetcher.feature.mainscreen.di.mainScreenModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application () {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(networkModule, mainScreenModel, bookmarksModule, databaseModule)
        }


         // ИСПОЛЬЗОВАНИЕ ТЁМНОЙ ТЕМЫ В ЗАВИСИМОСТИ ОТ СИСТЕМЫ
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}