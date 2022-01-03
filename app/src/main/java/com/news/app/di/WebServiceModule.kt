package com.news.app.di

import com.news.app.BuildConfig
import com.news.app.features.home.data.remote.NewsApiService
import com.news.app.utils.WebServiceInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class WebServiceModule {
  @Singleton @Provides
  fun provideOkHttpClient(webServiceInterceptor: WebServiceInterceptor): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
      .connectTimeout(1, TimeUnit.MINUTES)
      .readTimeout(1, TimeUnit.MINUTES)
      .addInterceptor(webServiceInterceptor)
      .addInterceptor(logging)
      .build()
  }


  @Singleton @Provides
  fun provideWebServiceInterceptor(): WebServiceInterceptor {
    return WebServiceInterceptor()
  }

  private fun buildRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BuildConfig.MAIN_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Singleton @Provides
  fun provideMainRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return buildRetrofit(okHttpClient)
  }

  @Provides @Singleton
  fun provideNewsApiService(retrofit: Retrofit): NewsApiService {
    return retrofit.create(NewsApiService::class.java)
  }
}