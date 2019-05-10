package com.sun.moviesun.data.source

import com.sun.moviesun.data.source.remote.response.GenresResponse
import com.sun.moviesun.data.source.remote.response.MovieResponse
import io.reactivex.Observable

interface MovieDataSource {
  interface Local {}

  interface Remote {
    fun getMoviesTrendingByDay(): Observable<MovieResponse>
    fun getMoviesCategory(category: String?, page: Int): Observable<MovieResponse>
    fun getGenres(): Observable<GenresResponse>
    fun getMoviesByGenre(genreId: Int, page: Int): Observable<MovieResponse>
    fun searchMovie(keyword: String, page: Int): Observable<MovieResponse>
  }
}
