package com.sun.moviesun.data.repository

import com.sun.moviesun.data.source.MovieDataSource
import com.sun.moviesun.data.source.local.MovieLocalDataSource
import com.sun.moviesun.data.source.remote.MovieRemoteDataSource
import com.sun.moviesun.data.source.remote.response.GenresResponse
import com.sun.moviesun.data.source.remote.response.MovieResponse
import io.reactivex.Observable

class MovieRepository constructor(
    private val local: MovieLocalDataSource,
    private val remote: MovieRemoteDataSource
) : MovieDataSource.Local, MovieDataSource.Remote {

  override fun getMoviesTrendingByDay(): Observable<MovieResponse> =
      remote.getMoviesTrendingByDay()

  override fun getMoviesCategory(category: String?, page: Int): Observable<MovieResponse> =
      remote.getMoviesCategory(category, page)

  override fun getGenres(): Observable<GenresResponse> =
      remote.getGenres()

  override fun getMoviesByGenre(genreId: Int, page: Int): Observable<MovieResponse> =
      remote.getMoviesByGenre(genreId, page)

  companion object {
    private var sInstance: MovieRepository? = null

    @JvmStatic
    fun getInstance(local: MovieLocalDataSource, remote: MovieRemoteDataSource): MovieRepository {
      if (sInstance == null) {
        synchronized(MovieRepository::javaClass) {
          sInstance = MovieRepository(local, remote)
        }
      }
      return sInstance!!
    }

    fun clearInstance() { sInstance = null }
  }
}
