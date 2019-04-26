package com.sun.moviesun.data.repository

import com.sun.moviesun.data.annotation.CategoryKey
import com.sun.moviesun.data.source.local.MovieLocalDataSource
import com.sun.moviesun.data.source.remote.MovieRemoteDataSource
import com.sun.moviesun.data.source.remote.response.MovieResponse
import io.reactivex.Observable

interface MovieRepository {
  fun getMoviesTrendingByDay(): Observable<MovieResponse>
  fun getMoviesCategory(@CategoryKey category: String, page: Int): Observable<MovieResponse>
}

class MovieRepositoryImpl constructor(
    private val local: MovieLocalDataSource,
    private val remote: MovieRemoteDataSource
) : MovieRepository {

  override fun getMoviesTrendingByDay(): Observable<MovieResponse> =
      remote.getMoviesTrendingByDay()

  override fun getMoviesCategory(@CategoryKey category: String, page: Int): Observable<MovieResponse> =
      remote.getMoviesCategory(category, page)


  companion object {
    private var sInstance: MovieRepositoryImpl? = null

    @JvmStatic
    fun getInstance(local: MovieLocalDataSource, remote: MovieRemoteDataSource): MovieRepository {
      if (sInstance == null) {
        synchronized(MovieRepositoryImpl::javaClass) {
          sInstance = MovieRepositoryImpl(local, remote)
        }
      }
      return sInstance!!
    }

    fun clearInstance() { sInstance = null }
  }
}
