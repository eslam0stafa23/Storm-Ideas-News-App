package com.news.app.features.home.data

import com.news.app.features.home.data.models.Article
import com.news.app.features.home.data.remote.NewsApiService
import com.news.app.features.home.domain.MainRepository
import com.news.app.utils.result.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val newsApiService: NewsApiService) :
  MainRepository {
  override fun getTopHeadlines(category: String): Flow<Resource<List<Article>>> {
    return flow {
      emit(Resource.loading())
      try {
        val apiResponse = newsApiService.getTopHeadlines(category)
        emit(Resource.success(apiResponse.articles))
      } catch (e: IOException) {
        emit(Resource.error())
      }
    }
  }
}

