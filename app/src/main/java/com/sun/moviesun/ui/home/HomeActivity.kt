package com.sun.moviesun.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sun.moviesun.R
import com.sun.moviesun.util.extension.setUpToolbar
import kotlinx.android.synthetic.main.home_activity.*
import org.jetbrains.anko.toast

class HomeActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.home_activity)
    setUpToolbar(toolbar, getString(R.string.app_name))
    initializeEvent()
  }

  private fun initializeEvent() {
    menuBottom.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener())
  }

  private fun onNavigationItemSelectedListener() = BottomNavigationView.OnNavigationItemSelectedListener { item ->
    when (item.itemId) {
      R.id.actionDiscover,
      R.id.actionGenre,
      R.id.actionFavorite,
      R.id.actionActor,
      R.id.actionSetting -> {
        toast(getString(R.string.text_coming_soon))
        return@OnNavigationItemSelectedListener true
      }
    }
    false
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.option_menu_home, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.actionSearch -> {
        toast(getString(R.string.text_coming_soon))
        return true
      }
    }
    return super.onOptionsItemSelected(item)
  }
}
