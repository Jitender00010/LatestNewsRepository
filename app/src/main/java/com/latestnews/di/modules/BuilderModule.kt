package com.latestnews.di.modules

import com.latestnews.MainActivity
import com.latestnews.view.LatestNewsListActivity
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class BuilderModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun contributeLatestNewsListActivity(): LatestNewsListActivity
}