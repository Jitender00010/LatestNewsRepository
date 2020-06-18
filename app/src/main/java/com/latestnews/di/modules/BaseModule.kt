package com.callmanagerfinal.di.modules

import android.content.Context
import com.callmanagerfinal.CallManagerApplication
import com.callmanagerfinal.di.qualifier.Application
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule


@Module(includes = [AndroidSupportInjectionModule::class])
abstract class BaseModule {

    @Binds
    @Application
    internal abstract fun context(application: CallManagerApplication): Context

}