package com.sun.moviesun.ui.home.genres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sun.moviesun.R
import com.sun.moviesun.databinding.GenresFragmentBinding
import com.sun.moviesun.util.extension.provideMovieRepository

class GenresFragment : Fragment() {

  private lateinit var genresBinding: GenresFragmentBinding
  private lateinit var genresViewModel: GenresViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    genresViewModel = GenresViewModel(activity!!.applicationContext.provideMovieRepository(), childFragmentManager)
    genresBinding = DataBindingUtil.inflate(inflater, R.layout.genres_fragment, container, false)
    initializeUI()
    return genresBinding.root
  }

  private fun initializeUI() {
    genresBinding.run {
      viewModel = genresViewModel
      tabLayout.setupWithViewPager(pagerGenre, true)
    }
  }

  override fun onStop() {
    super.onStop()
    genresViewModel.onCleared()
  }

  companion object {
    fun newInstance() = GenresFragment()
  }
}
