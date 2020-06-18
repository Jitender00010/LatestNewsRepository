package com.latestnews.di.modules

import com.domain.repository.NewsRepository
import com.latestnews.data.repositoryImp.NewsRepositoryImp
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule {

    @Binds
    internal abstract fun bindNewsRepo(repository: NewsRepositoryImp): NewsRepository

}