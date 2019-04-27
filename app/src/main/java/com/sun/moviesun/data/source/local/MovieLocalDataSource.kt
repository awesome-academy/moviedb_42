package com.sun.moviesun.data.source.local

import com.google.common.base.Optional
import com.sun.moviesun.data.model.entity.Movie
import com.sun.moviesun.data.source.MovieDataSource
import com.sun.moviesun.data.source.local.dao.MovieDao
import io.reactivex.Flowable

class MovieLocalDataSource private constructor(
    private val movieDao: MovieDao
) : MovieDataSource.Local {

  override fun getMovies(page: Int, category: String): Flowable<List<Movie>> = movieDao.getMovies(page, category)

  override fun getMovie(movieId: Int, category: String): Flowable<Optional<Movie>> = movieDao.getMovie(movieId, category)

  override fun insertMovie(movie: Movie) { movieDao.insertMovie(movie) }

  override fun insertMovie(movies: List<Movie>) { movieDao.insertMovie(movies) }

  override fun updateMovie(movie: Movie) { movieDao.updateMovie(movie) }

  override fun deleteMovieById(movieId: Int): Int = movieDao.deleteMovieById(movieId)

  override fun deleteMovies() { movieDao.deleteMovies() }

  companion object {

    private var sInstance: MovieLocalDataSource? = null

    @JvmStatic
    fun getInstance(movieDao: MovieDao): MovieLocalDataSource {
      if (sInstance == null) {
        synchronized(MovieLocalDataSource::javaClass) {
          sInstance = MovieLocalDataSource(movieDao)
        }
      }
      return sInstance!!
    }

    fun clearInstance() { sInstance = null }
  }
}
