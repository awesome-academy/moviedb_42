package com.sun.moviesun.util.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.sun.moviesun.base.BaseAdapter
import com.sun.moviesun.data.model.entity.Movie
import com.sun.moviesun.ui.home.discover.SliderAdapter
import com.sun.moviesun.ui.home.genres.GenrePageAdapter

object BindingAdapter {

  @JvmStatic
  @BindingAdapter("bindSlideAdapter")
  fun bindSlideAdapter(pager: ViewPager, adapter: SliderAdapter) {
    pager.adapter = adapter
    pager.offscreenPageLimit = adapter.count
  }

  @JvmStatic
  @BindingAdapter("bindAdapter")
  fun <T> bindAdapter(recycler: RecyclerView?, adapter: BaseAdapter<T>) {
    recycler!!.adapter = adapter
  }

  @JvmStatic
  @BindingAdapter("bindPagerAdapter")
  fun bindGenrePagerAdapter(pager: ViewPager, adapter: GenrePageAdapter) {
    pager.adapter = adapter
    pager.offscreenPageLimit = adapter.count
  }
}
