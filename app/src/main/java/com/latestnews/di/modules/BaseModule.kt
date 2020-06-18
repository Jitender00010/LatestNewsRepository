package com.latestnews.di.modules

import android.content.Context
import com.latestnews.LatestNewsApplication
import com.latestnews.di.qualifier.Application
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule


@Module(includes = [AndroidSupportInjectionModule::class])
abstract class BaseModule {

    @Binds
    @Application
    internal abstract fun context(application: LatestNewsApplication): Context

}