package com.latestnews.di

import com.latestnews.LatestNewsApplication
import com.latestnews.di.modules.*
import com.latestnews.di.modules.viewModel.ViewModelModules
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [BaseModule::class,AndroidSupportInjectionModule::class,  ViewModelModules::class, BuilderModule::class, PersistenceModule::class, NetworkModules::class , RepoModule::class])

interface ApplicationComponent : AndroidInjector<LatestNewsApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<LatestNewsApplication>() {
    }
}