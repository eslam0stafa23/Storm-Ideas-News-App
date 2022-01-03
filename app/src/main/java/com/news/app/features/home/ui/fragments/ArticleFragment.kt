package com.news.app.features.home.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.news.app.R
import com.news.app.databinding.FragmentArticleBinding
import com.news.app.features.home.data.models.Article
import com.news.app.utils.appComponent
import com.news.app.utils.getTimeAgo
import com.news.app.utils.views.BaseFragment

/**
 * This is the Article Fragment that shows the article details,
 * @property args is passed for the fragment which holds the article object that will be shown on
 * the screen
 */
class ArticleFragment : BaseFragment<FragmentArticleBinding>() {
  private val args: ArticleFragmentArgs by navArgs()

  companion object {
    @JvmStatic
    fun show(navController: NavController, article: Article) {
      val action = ArticleFragmentDirections.actionGlobalOpenArticleFragment(article)
      navController.navigate(action)
    }
  }

  override fun onBind(
    inflater: LayoutInflater,
    container: ViewGroup?
  ): FragmentArticleBinding {
    appComponent.inject(this)
    return FragmentArticleBinding.inflate(inflater, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    feedDataToViews(args.article)
    setClickListeners()
  }

  private fun setClickListeners() {
    binding.ivBackButton.setOnClickListener { navController.popBackStack() }
    binding.btnShareArticle.setOnClickListener {
      val shareIntent = Intent().apply {
        type = "text/plain"
        action = Intent.ACTION_SEND
      }
      shareIntent.putExtra(Intent.EXTRA_TEXT, args.article.url)
      startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)))
    }
  }

  private fun feedDataToViews(article: Article) {
    binding.tvArticleTitle.text = article.title
    binding.tvArticleSource.text = article.source.name
    binding.tvArticleAuthor.text = getString(R.string.author, article.author)
    binding.tvArticleTime.text = article.publishedAt.getTimeAgo()
    binding.tvArticleDescription.text = article.description
    binding.tvArticleContent.text = article.content
    Glide.with(requireContext())
      .load(article.urlToImage)
      .placeholder(R.drawable.img_placeholder)
      .into(binding.ivArticleImage)
  }

}
