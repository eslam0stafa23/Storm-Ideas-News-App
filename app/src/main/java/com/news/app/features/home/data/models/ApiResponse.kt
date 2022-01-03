package com.news.app.features.home.data.models

import com.google.gson.annotations.SerializedName

data class ApiResponse(
  @SerializedName("articles") val articles: List<Article>,
  @SerializedName("status") val status: String,
  @SerializedName("totalResults") val totalResults: Int
)