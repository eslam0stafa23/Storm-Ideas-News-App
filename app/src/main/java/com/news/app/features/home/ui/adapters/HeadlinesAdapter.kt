package com.news.app.features.home.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.news.app.R
import com.news.app.databinding.ItemHeadlineBinding
import com.news.app.features.home.data.models.Article
import com.news.app.features.home.ui.adapters.HeadlinesAdapter.HeadlineViewHolder
import com.news.app.utils.layoutInflater

/**
 * This is the adapter used for the headlines scrollView it takes :
 * @property articleActionsListener ArticleActionsListener which is used to control the actions
 * performed on the item inside the adapter
 *
 */
class HeadlinesAdapter(private val articleActionsListener: ArticleActionsListener) :
  ListAdapter<Article, HeadlineViewHolder>(callback) {

  companion object {
    private val callback = object : DiffUtil.ItemCallback<Article>() {
      override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.url == newItem.url

      override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder {
    val binding = ItemHeadlineBinding.inflate(parent.layoutInflater, parent, false)
    return HeadlineViewHolder(binding)
  }

  override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class HeadlineViewHolder(private val binding: ItemHeadlineBinding) :
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

  interface ArticleActionsListener {
    fun onArticleClick(article: Article)
  }
}