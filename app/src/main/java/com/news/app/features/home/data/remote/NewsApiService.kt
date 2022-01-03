package com.news.app.features.home.data.remote

import com.news.app.features.home.data.models.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
  @GET("top-headlines")
  suspend fun getTopHeadlines(
    @Query("category") category: String,
    @Query("language") language: String = "en"
  ): ApiResponse
}