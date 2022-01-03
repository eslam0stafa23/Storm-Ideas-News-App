package com.news.app.features.home.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.app.databinding.FragmentNewsCategoryBinding
import com.news.app.features.home.data.models.Article
import com.news.app.features.home.ui.adapters.HeadlinesAdapter
import com.news.app.features.home.ui.adapters.HeadlinesAdapter.ArticleActionsListener
import com.news.app.features.home.ui.adapters.MostRecentAdapter
import com.news.app.features.home.ui.viewmodels.MainViewModel
import com.news.app.utils.appComponent
import com.news.app.utils.result.ResourceType.LOADING
import com.news.app.utils.result.ResourceType.SUCCESS
import com.news.app.utils.viewmodel.ViewModelFactory
import com.news.app.utils.views.BaseFragment
import javax.inject.Inject

/**
 * This is the fragment that is used inside the view pager, a newInstance is created of this
 * fragment for each news category
 * @property viewModelFactory ViewModelFactory is used to init the main viewModel
 * @property mainViewModel MainViewModel
 * @property headlinesAdapter HeadlinesAdapter to attach to the headlines recycler view
 * @property mostRecentAdapter MostRecentAdapter to attach to the most recent recycler view
 */
class NewsCategoryFragment : BaseFragment<FragmentNewsCategoryBinding>(), ArticleActionsListener {

  @Inject lateinit var viewModelFactory: ViewModelFactory
  private lateinit var mainViewModel: MainViewModel
  private val headlinesAdapter: HeadlinesAdapter by lazy { HeadlinesAdapter(this) }
  private val mostRecentAdapter: MostRecentAdapter by lazy { MostRecentAdapter(this) }

  override fun onBind(
    inflater: LayoutInflater,
    container: ViewGroup?
  ): FragmentNewsCategoryBinding {
    appComponent.inject(this)
    return FragmentNewsCategoryBinding.inflate(inflater, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRecyclerViews()
    initViewModel()
    setupObservers()
  }

  /**
   * this method is used to setup the basics for the recycler views
   */
  private fun setupRecyclerViews() {
    binding.rvHeadlines.layoutManager =
      LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    binding.rvHeadlines.adapter = headlinesAdapter

    binding.rvMostRecent.layoutManager = LinearLayoutManager(context)
    binding.rvMostRecent.adapter = mostRecentAdapter
  }

  private fun initViewModel() {
    mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java).apply {
      setCategory(arguments?.getString(ARG_CATEGORY_NAME) ?: "")
    }
  }

  /**
   * this method is used to setup the observer on the top headlines on the viewModel and then
   * submit the resulted data to the adapters in case of success.
   * A sublist consists of the first 3 items is submitted to the headlines adapter and the rest of
   * the list is submitted to the most recent adapter, and this is just for the purpose of
   * matching the design.
   */
  private fun setupObservers() {
    mainViewModel.topHeadlines().observe(viewLifecycleOwner, {
      when (it.resourceType) {
        SUCCESS -> {
          setLoading(false)
          headlinesAdapter.submitList(it.data?.subList(0, 3))
          mostRecentAdapter.submitList(it.data?.subList(3, it.data?.size!!))
        }
        LOADING -> {
          setLoading(true)
        }
      }
    })
  }

  private fun setLoading(isLoading: Boolean) {
    if (isLoading) {
      binding.layoutShimmerHeadlines.startShimmer()
      binding.layoutShimmerMostRecent.startShimmer()
    } else {
      binding.layoutShimmerHeadlines.stopShimmer()
      binding.layoutShimmerHeadlines.visibility = View.INVISIBLE
      binding.layoutShimmerMostRecent.stopShimmer()
      binding.layoutShimmerMostRecent.visibility = View.INVISIBLE
      binding.rvHeadlines.visibility = View.VISIBLE
    }
  }

  companion object {
    /**
     * The argument represents the news category for this fragment.
     */
    private const val ARG_CATEGORY_NAME = "category-name"

    /**
     * Returns a new instance of this fragment for the given news category.
     */
    @JvmStatic
    fun newInstance(category: String): NewsCategoryFragment {
      return NewsCategoryFragment().apply {
        arguments = Bundle().apply {
          putString(ARG_CATEGORY_NAME, category)
        }
      }
    }
  }

  /**
   * this method is used to open the ArticleFragment to show the Article details
   * @param article Article
   */
  override fun onArticleClick(article: Article) {
    ArticleFragment.show(navController, article)
  }
}