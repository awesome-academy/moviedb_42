package com.sun.moviesun.data.source

import com.google.common.base.Optional
import com.sun.moviesun.data.model.entity.Movie
import com.sun.moviesun.data.model.network.MovieResponse
import com.sun.moviesun.data.source.remote.ApiResponse
import io.reactivex.Flowable

interface MovieDataSource {
  interface Local {
    fun getMovies(page: Int, category: String): Flowable<List<Movie>>
    fun getMovie(movieId: Int, category: String): Flowable<Optional<Movie>>
    fun insertMovie(movie: Movie)
    fun insertMovie(movies: List<Movie>)
    fun updateMovie(movie: Movie)
    fun deleteMovieById(movieId: Int): Int
    fun deleteMovies()
  }

  interface Remote {
    fun getMoviesTrendingByDay(): Flowable<ApiResponse<MovieResponse>>
    fun getMoviesCategory(category: String?, page: Int): Flowable<ApiResponse<MovieResponse>>
  }
}
