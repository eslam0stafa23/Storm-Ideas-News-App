package com.news.app.features.home.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.news.app.databinding.FragmentHomeBinding
import com.news.app.features.home.ui.adapters.SectionsPagerAdapter
import com.news.app.utils.appComponent
import com.news.app.utils.views.BaseFragment

/**
 * This is the home fragment which hosts the tab layout and it's view pager
 * it's the startDestination of the navigation component in the app
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

  override fun onBind(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
    appComponent.inject(this)
    return FragmentHomeBinding.inflate(inflater, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.viewPager.adapter = SectionsPagerAdapter(context, childFragmentManager)
    binding.tabs.setupWithViewPager(binding.viewPager)
  }
}