package com.yazdanmanesh.githubcompose.di

import com.yazdanmanesh.githubcompose.domain.repositories.GithubRepository
import com.yazdanmanesh.githubcompose.data.repository.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(githubRepositoryImpl: GithubRepositoryImpl): GithubRepository
}