package com.sun.moviesun.ui.home.discover

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.sun.moviesun.data.model.entity.Movie
import com.sun.moviesun.data.repository.MovieRepository
import com.sun.moviesun.data.annotation.CategoryKeyDef
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DiscoverViewModel constructor(
    private val movieRepository: MovieRepository
) : BaseObservable() {

  val topTrendingMoviesObservable: ObservableList<Movie> = ObservableArrayList()
  val popularMoviesObservable: ObservableList<Movie> = ObservableArrayList()
  val nowPlayingMoviesObservable: ObservableList<Movie> = ObservableArrayList()
  val upComingMoviesObservable: ObservableList<Movie> = ObservableArrayList()
  val topRateMoviesObservable: ObservableList<Movie> = ObservableArrayList()
  private val compositeDisposable: CompositeDisposable = CompositeDisposable()

  init {
    loadData()
  }

  fun loadData() {
    loadTrendingMovies()
    loadMovies(CategoryKeyDef.TOP_RATED)
    loadMovies(CategoryKeyDef.NOW_PLAYING)
    loadMovies(CategoryKeyDef.POPULAR)
    loadMovies(CategoryKeyDef.UPCOMING)
  }

  fun loadTrendingMovies() {
    val disposable = movieRepository.getMoviesTrendingByDay()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
              topTrendingMoviesObservable.clear()
              topTrendingMoviesObservable.addAll(it.results.subList(FIRST_INDEX, LAST_INDEX))
            }
            ,
            { throwable -> handleError(throwable) })
    compositeDisposable.add(disposable)
  }

  fun loadMovies(category: String = CategoryKeyDef.DEFAULT, page: Int = DEFAULT_PAGE) {
    val disposable = movieRepository.getMoviesCategory(category, page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
              when (category) {
                CategoryKeyDef.TOP_RATED -> {
                  topRateMoviesObservable.clear()
                  topRateMoviesObservable.addAll(it.results)
                }
                CategoryKeyDef.NOW_PLAYING -> {
                  nowPlayingMoviesObservable.clear()
                  nowPlayingMoviesObservable.addAll(it.results)
                }
                CategoryKeyDef.POPULAR -> {
                  popularMoviesObservable.clear()
                  popularMoviesObservable.addAll(it.results)
                }
                CategoryKeyDef.UPCOMING -> {
                  upComingMoviesObservable.clear()
                  upComingMoviesObservable.addAll(it.results)
                }
              }
            },
            { throwable -> handleError(throwable) })
    compositeDisposable.add(disposable)
  }

  fun handleError(t: Throwable) {

  }

  fun onCleared() {
    compositeDisposable.clear()
  }

  companion object {
    private const val DEFAULT_PAGE = 1
    private const val FIRST_INDEX = 0
    private const val LAST_INDEX = 5
  }
}
