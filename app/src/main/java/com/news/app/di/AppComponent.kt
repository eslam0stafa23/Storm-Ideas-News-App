package com.news.app.di

import com.news.app.features.home.ui.activities.MainActivity
import com.news.app.features.home.ui.fragments.ArticleFragment
import com.news.app.features.home.ui.fragments.HomeFragment
import com.news.app.features.home.ui.fragments.NewsCategoryFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  modules = [ViewModelModule::class, RepositoriesModule::class, WebServiceModule::class]
)
interface AppComponent {

  fun inject(mainActivity: MainActivity)

  fun inject(homeFragment: HomeFragment)
  fun inject(newsCategoryFragment: NewsCategoryFragment)
  fun inject(articleFragment: ArticleFragment)

  @Component.Factory
  interface Factory {
    fun create(): AppComponent
  }
}