package com.sun.moviesun.ui.home.genres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sun.moviesun.R

class GenreFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.genre_fragment, container, false)
  }

  companion object {
    private const val ARGUMENT_GENRE_ID = "com.sun.moviesun.ARGUMENT_GENRE_ID"
    fun newInstance(genreId: Int) = GenreFragment().apply {
      arguments = Bundle().apply {
        putInt(ARGUMENT_GENRE_ID, genreId)
      }
    }
  }
}
