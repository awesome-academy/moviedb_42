package com.sun.moviesun.util.extension

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun AppCompatActivity.setUpToolbar(toolbar: Toolbar, titleText: String = "") {
  setSupportActionBar(toolbar)
  supportActionBar?.run {
    title = titleText
  }
}

fun Context.isNetworkStatusAvailable(): Boolean {
  val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
  return connectivityManager?.activeNetworkInfo?.isConnected ?: false
}
