package com.sun.moviesun.ui.home.genres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sun.moviesun.R

class GenresFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.genres_fragment, container, false)
  }

  companion object {
    fun newInstance() = GenresFragment()
  }
}
