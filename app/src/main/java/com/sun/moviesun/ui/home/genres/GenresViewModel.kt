package com.sun.moviesun.ui.home.genres

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.fragment.app.FragmentManager
import com.sun.moviesun.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GenresViewModel constructor(
    private val movieRepository: MovieRepository,
    private val fm: FragmentManager
) : BaseObservable() {

  val adapterGenreObservable: ObservableField<GenrePageAdapter> = ObservableField()
  val compositeDisposable: CompositeDisposable = CompositeDisposable()

  init {
    setUpAdapter()
    loadData()
  }

  private fun setUpAdapter() {
    adapterGenreObservable.set(GenrePageAdapter(fm))
  }

  fun loadData() {
    loadGenres()
  }

  private fun loadGenres() {
    val disposable = movieRepository.getGenres()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          adapterGenreObservable.get()!!.updateData(it.genres)
        }, { throwable -> handleError(throwable) })
    compositeDisposable.add(disposable)
  }

  fun handleError(t: Throwable) {

  }

  fun onCleared() {
    compositeDisposable.clear()
  }
}
