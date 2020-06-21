package com.latestnews.di.modules

import com.latestnews.view.LatestNews.LatestNewsListActivity
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class BuilderModule {

    @ContributesAndroidInjector()
    abstract fun contributeLatestNewsListActivity(): LatestNewsListActivity
}