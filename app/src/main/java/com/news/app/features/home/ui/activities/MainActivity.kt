package com.news.app.features.home.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.news.app.R
import com.news.app.databinding.ActivityMainBinding
import com.news.app.utils.appComponent

/**
 * This is the main activity of the app and it hosts the navigation fragment
 *
 * @property binding ActivityMainBinding
 * @property navController NavController
 */

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var navController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    appComponent.inject(this)
    setupNavigationComponent()
  }

  /**
   * this method is used to init the navController in case we needed it to control navigation
   * from this activity
   */
  private fun setupNavigationComponent() {
    navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main)
  }

  override fun onSupportNavigateUp(): Boolean {
    return NavigationUI.navigateUp(navController, null)
  }
}