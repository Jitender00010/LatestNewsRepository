package com.callmanagerfinal.di.modules


import com.callmanagerfinal.MainActivity
import com.callmanagerfinal.view.login.LoginActivity
import com.callmanagerfinal.view.splash.SplashActivity
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class BuilderModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector()
    abstract fun contributeLoginActivity(): LoginActivity
}