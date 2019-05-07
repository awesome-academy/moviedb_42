package com.sun.moviesun.ui.home.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.sun.moviesun.R
import com.sun.moviesun.data.model.entity.Movie
import com.sun.moviesun.databinding.DiscoverFragmentBinding
import com.sun.moviesun.util.extension.provideMovieRepository

class DiscoverFragment : Fragment(), SliderAdapter.Listener,
    ViewPager.OnPageChangeListener {

  private lateinit var discoverBinding: DiscoverFragmentBinding
  private lateinit var discoverViewModel: DiscoverViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    discoverViewModel = DiscoverViewModel(activity!!.applicationContext.provideMovieRepository())
    discoverBinding = DataBindingUtil.inflate(inflater, R.layout.discover_fragment, container, false)
    initializeUI()
    return discoverBinding.root
  }

  private fun initializeUI() {
    discoverBinding.run {
      viewModel = discoverViewModel
      pagerSlider.adapter = SliderAdapter(this@DiscoverFragment)
      indicator.setupWithViewPager(pagerSlider, true)
      pagerSlider.addOnPageChangeListener(this@DiscoverFragment)
    }
  }

  override fun onStop() {
    super.onStop()
    discoverViewModel.onCleared()
  }

  override fun onTopTrendingItemClick(movie: Movie) {

  }

  override fun onPageScrollStateChanged(state: Int) {
  }

  override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
  }

  override fun onPageSelected(position: Int) {
    SliderAdapter.setCurrentPosition(position)
  }

  companion object {
    fun newInstance() = DiscoverFragment()
  }
}

