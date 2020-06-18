package com.callmanagerfinal.di

import android.app.Application
import com.callmanagerfinal.CallManagerApplication
import com.callmanagerfinal.di.modules.*
import com.callmanagerfinal.di.modules.ViewModel.ViewModelModules
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [BaseModule::class,AndroidSupportInjectionModule::class,  ViewModelModules::class, BuilderModule::class, PersistenceModule::class, NetworkModules::class ,RepoModule::class])

interface ApplicationComponent : AndroidInjector<CallManagerApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<CallManagerApplication>() {
    }
}