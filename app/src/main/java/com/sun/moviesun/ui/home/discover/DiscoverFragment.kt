package com.sun.moviesun.ui.home.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sun.moviesun.R
import com.sun.moviesun.databinding.DiscoverFragmentBinding
import com.sun.moviesun.util.extension.provideMovieRepository

class DiscoverFragment : Fragment() {

  private lateinit var discoverBinding: DiscoverFragmentBinding
  private lateinit var discoverViewModel: DiscoverViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    discoverViewModel = DiscoverViewModel(activity!!.applicationContext.provideMovieRepository())
    discoverBinding = DataBindingUtil.inflate(inflater, R.layout.discover_fragment, container, false)
    initializeUI()
    return discoverBinding.root
  }

  private fun initializeUI() {

  }

  override fun onStop() {
    super.onStop()
    discoverViewModel.onCleared()
  }

  companion object {
    fun newInstance() = DiscoverFragment()
  }
}

