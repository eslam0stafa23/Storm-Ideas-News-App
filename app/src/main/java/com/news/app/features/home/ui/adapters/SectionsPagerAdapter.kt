package com.news.app.features.home.ui.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.news.app.R
import com.news.app.features.home.ui.annotations.Categories
import com.news.app.features.home.ui.fragments.NewsCategoryFragment

private val TAB_TITLES = arrayOf(
  R.string.general,
  R.string.technology,
  R.string.business,
  R.string.sports,
  R.string.science,
  R.string.health,
  R.string.entertainment,
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the news categories.
 */
class SectionsPagerAdapter(private val context: Context?, manger: FragmentManager) :
  FragmentPagerAdapter(manger) {

  override fun getItem(position: Int): Fragment {
    return when (position) {
      1 -> NewsCategoryFragment.newInstance(Categories.TECHNOLOGY)
      2 -> NewsCategoryFragment.newInstance(Categories.BUSINESS)
      3 -> NewsCategoryFragment.newInstance(Categories.SPORTS)
      4 -> NewsCategoryFragment.newInstance(Categories.SCIENCE)
      5 -> NewsCategoryFragment.newInstance(Categories.HEALTH)
      6 -> NewsCategoryFragment.newInstance(Categories.ENTERTAINMENT)
      else -> NewsCategoryFragment.newInstance(Categories.GENERAL)
    }
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return context?.resources?.getString(TAB_TITLES[position])
  }

  override fun getCount(): Int {
    return TAB_TITLES.size
  }
}