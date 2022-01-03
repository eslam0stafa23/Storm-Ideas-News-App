package com.news.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.news.app.di.AppComponent
import com.news.app.di.DaggerAppComponent

class App : Application() {
  lateinit var appComponent: AppComponent
    private set

  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerAppComponent.factory().create()
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
  }
}