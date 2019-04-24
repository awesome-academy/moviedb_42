package com.sun.moviesun.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun AppCompatActivity.setUpToolbar(toolbar: Toolbar, titleText: String = "") {
  setSupportActionBar(toolbar)
  supportActionBar?.run {
    title = titleText
  }
}
