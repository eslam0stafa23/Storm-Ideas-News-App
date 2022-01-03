package com.news.app.di

import androidx.lifecycle.ViewModel
import com.news.app.di.annotitions.ViewModelKey
import com.news.app.features.home.domain.GetTopHeadlinesUseCase
import com.news.app.features.home.ui.viewmodels.MainViewModel
import com.news.app.utils.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
class ViewModelModule {

  @Provides
  fun provideViewModelFactory(providerMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory {
    return ViewModelFactory(providerMap)
  }

  @Provides @IntoMap @ViewModelKey(MainViewModel::class)
  fun provideMainViewModel(
    getTopHeadlinesUseCase: GetTopHeadlinesUseCase
  ): ViewModel {
    return MainViewModel(getTopHeadlinesUseCase)
  }
}