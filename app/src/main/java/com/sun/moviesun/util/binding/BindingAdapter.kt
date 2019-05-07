package com.sun.moviesun.util.binding

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.sun.moviesun.data.model.entity.Movie
import com.sun.moviesun.ui.home.discover.SliderAdapter

object BindingAdapter {

  @JvmStatic
  @BindingAdapter("bindPagerAdapter")
  fun bindPagerAdapter(pager: ViewPager, movies: List<Movie>) {
    val adapter = pager.adapter as SliderAdapter
    if (movies.isNotEmpty()) {
      adapter.updateData(movies)
      pager.offscreenPageLimit = adapter.count
    }
  }
}
