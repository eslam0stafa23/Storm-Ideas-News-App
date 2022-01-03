package com.news.app.features.home.domain

import com.news.app.features.home.data.models.Article
import com.news.app.utils.result.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(private val mainRepository: MainRepository) {

  fun get(category: String): Flow<Resource<List<Article>>> =
    mainRepository.getTopHeadlines(category)
}