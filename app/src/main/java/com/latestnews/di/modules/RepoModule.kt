package com.callmanagerfinal.di.modules

import com.callmanagerfinal.data.repositoryImp.AppSessionRepositoryImp
import com.callmanagerfinal.data.repositoryImp.UserRepositoryImp
import com.e.domain.repository.AppSessionRepository
import com.e.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule {

    @Binds
    internal abstract fun bindAppSessionRepo(repository: AppSessionRepositoryImp): AppSessionRepository

    @Binds
    internal abstract fun bindUserRepository(repository: UserRepositoryImp): UserRepository

}