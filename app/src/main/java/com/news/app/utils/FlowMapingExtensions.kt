package com.news.app.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.news.app.utils.result.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

fun <T> Flow<Resource<T>>.asMappedResourceLiveData(
  tag: String = "",
  coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
): LiveData<Resource<T>> {
  return onStart { emit(Resource.loading()) }
    .catch {
      loge("$tag: onError:: ${it.message}", it)
      emit(Resource.error(it.message))
    }
    .asLiveData(coroutineDispatcher)
}