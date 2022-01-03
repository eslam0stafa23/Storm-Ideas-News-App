package com.news.app.di

import com.news.app.features.home.data.MainRepositoryImpl
import com.news.app.features.home.domain.MainRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoriesModule {
  @Singleton @Binds
  abstract fun bindMainRepository(mainRepository: MainRepositoryImpl?): MainRepository?
}