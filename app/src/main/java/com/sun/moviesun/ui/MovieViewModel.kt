package com.sun.moviesun.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sun.moviesun.BR
import com.sun.moviesun.data.model.entity.Movie

class MovieViewModel : BaseObservable() {

  private lateinit var movie: Movie

  fun setMovie(movie: Movie) {
    this.movie = movie
    notifyPropertyChanged(BR.movie)
  }

  @Bindable fun getMovie() = movie
}
