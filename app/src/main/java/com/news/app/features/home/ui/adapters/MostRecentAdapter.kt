package com.news.app.features.home.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.news.app.R
import com.news.app.databinding.ItemMostRecentBinding
import com.news.app.features.home.data.models.Article
import com.news.app.features.home.ui.adapters.HeadlinesAdapter.ArticleActionsListener
import com.news.app.features.home.ui.adapters.MostRecentAdapter.MostRecentViewHolder
import com.news.app.utils.layoutInflater

/**
 * This is the adapter used for the most recent scrollView it takes :
 * @property articleActionsListener ArticleActionsListener which is used to control the actions
 * performed on the item inside the adapter
 *
 */
class MostRecentAdapter(private val articleActionsListener: ArticleActionsListener) :
  ListAdapter<Article, MostRecentViewHolder>(callback) {

  companion object {
    private val callback = object : DiffUtil.ItemCallback<Article>() {
      override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.url == newItem.url

      override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostRecentViewHolder {
    val binding = ItemMostRecentBinding.inflate(parent.layoutInflater, parent, false)
    return MostRecentViewHolder(binding)
  }

  override fun onBindViewHolder(holder: MostRecentViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class MostRecentViewHolder(private val binding: ItemMostRecentBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article) {
      binding.tvArticleTitle.text = article.title
      Glide.with(binding.root)
        .load(article.urlToImage)
        .placeholder(R.drawable.img_placeholder)
        .into(binding.ivHeadlineImage)
      binding.layoutRoot.setOnClickListener { articleActionsListener.onArticleClick(article) }
    }
  }
}