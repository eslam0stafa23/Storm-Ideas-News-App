package com.news.app

import android.app.Application
import com.news.app.di.AppComponent
import com.news.app.di.DaggerAppComponent

class App : Application() {
  lateinit var appComponent: AppComponent
    private set

  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerAppComponent.factory().create()
  }
}