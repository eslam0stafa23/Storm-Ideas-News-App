package com.news.app.features.home.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.news.app.features.home.data.models.Article
import com.news.app.features.home.domain.GetTopHeadlinesUseCase
import com.news.app.utils.asMappedResourceLiveData
import com.news.app.utils.result.Resource
import javax.inject.Inject

class MainViewModel @Inject constructor(
  var getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
) : ViewModel() {

  private val category = MutableLiveData<String>()

  fun setCategory(category: String) {
    this.category.value = category
  }

  fun topHeadlines(): LiveData<Resource<List<Article>>> =
    getTopHeadlinesUseCase.get(category.value!!).asMappedResourceLiveData("getTopHeadlines")
}