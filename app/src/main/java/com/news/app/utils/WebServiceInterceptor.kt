package com.news.app.utils

import com.news.app.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Singleton

@Singleton
class WebServiceInterceptor : Interceptor {

  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val requestBuilder = chain.request().newBuilder()
    requestBuilder.addHeader(
      Constants.AUTHORIZATION,
      "${Constants.TOKEN_TYPE}${BuildConfig.API_KEY}"
    )
    return chain.proceed(requestBuilder.build())
  }
}