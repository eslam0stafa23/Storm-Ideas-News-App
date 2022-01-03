package com.news.app.features.home.domain

import com.news.app.features.home.data.models.Article
import com.news.app.utils.result.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {
  fun getTopHeadlines(category:String): Flow<Resource<List<Article>>>
}